package dev.husein.quinots.service;

import com.google.common.base.Strings;
import dev.husein.quinots.adapter.TagsConverter;
import dev.husein.quinots.dto.NoteDTO;
import dev.husein.quinots.exception.QuinotsException;
import dev.husein.quinots.model.BaseResponse;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.model.User;
import dev.husein.quinots.repository.NoteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class NoteService {
    private static final Logger LOGGER = LoggerFactory.getLogger(NoteService.class);

    private UserService userService;
    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository, UserService userService) {
        this.noteRepository = noteRepository;
        this.userService = userService;
    }

    public BaseResponse createNote(String username, String title, String description, List<String> tags) {
        LOGGER.debug("Creating Note");
        LOGGER.debug("\nTitle: {} \nDescription: {} \nTags: {}", title, description, tags);
        LOGGER.debug("User: {}", username);

        BaseResponse baseResponse = new BaseResponse();
        baseResponse.addNoteToList(noteRepository.saveAndFlush(new Note(title, description, tags, userService.verifyUser(username))));
        return baseResponse;
    }

    public void deleteNote(String username, Long id) {
        Long userId = userService.getUserIdByUsername(username);
        Note note = noteRepository.getById(id);
        if (userId.equals(note.getUser().getId())) {
            noteRepository.deleteById(id);
        } else {
            throw new QuinotsException(String.format("Note with ID %d can't be found", id));
        }
    }

    public void updateNote(String username, Long id, NoteDTO newEditedNote) {
        Long userId = userService.getUserIdByUsername(username);

        if (noteRepository.existsById(id)) {
            Note existingNote = noteRepository.getById(id);
            // check every field to make sure you don't override the field if it is empty

            if (!userId.equals(existingNote.getUser().getId())) {
                throw new QuinotsException(String.format("Note with ID %d can't be found", id));
            }

            if (!Strings.isNullOrEmpty(newEditedNote.getTitle())) {
                existingNote.setTitle(newEditedNote.getTitle());
            }

            if (!Strings.isNullOrEmpty(newEditedNote.getDescription())) {
                existingNote.setDescription(newEditedNote.getDescription());
            }

            if (newEditedNote.getTags() != null && newEditedNote.getTags().size() > 0) {
                existingNote.setTags(newEditedNote.getTags());
            }

            noteRepository.saveAndFlush(existingNote);

        } else {
            throw new QuinotsException(String.format("Note with ID %d can't be found", id));
        }
    }

    public BaseResponse listNotesForUser(String username) {
        return new BaseResponse(noteRepository.listNotesForUser(userService.getUserIdByUsername(username)));
    }

    public BaseResponse searchNotes(String username, String includingWords, Calendar fromDate, Calendar toDate, String tagsStr) {
        BaseResponse baseResponse = new BaseResponse();
        List<String> tags = new ArrayList<>();

        if (!Strings.isNullOrEmpty(tagsStr)) {
            tags = Arrays.asList(tagsStr.split(TagsConverter.SEPARATOR_CHAR));
        }

        List<Note> listOfNotesWithAnyTag = noteRepository.searchNotes(userService.getUserIdByUsername(username), includingWords, fromDate, toDate);

        // if tag filter is requested
        if (tags.size() > 0) {
            // go through the list and remove every note that doesn't have any of the tags in its tags field
            List<Note> notesFilteredByTag = new ArrayList<>();

            // filter list of notes by tag
            List<String> finalTags = tags;
            listOfNotesWithAnyTag.stream().iterator().forEachRemaining(note -> {
                if (note.getTags().stream().anyMatch(finalTags::contains)) {
                    notesFilteredByTag.add(note);
                }
            });


            baseResponse.setNotes(notesFilteredByTag);
        } else {
            baseResponse.setNotes(listOfNotesWithAnyTag);
        }

        return baseResponse;
    }

    public BaseResponse getNoteById(String username, Long id) {
        Long userId = userService.getUserIdByUsername(username);
        BaseResponse baseResponse = new BaseResponse();
        if (noteRepository.existsById(id)) {
            Note note = noteRepository.findById(id).orElse(null);
            if (userId.equals(note.getUser().getId()))
                baseResponse.addNoteToList(note);
            else
                throw new QuinotsException(String.format("Note with ID %d can't be found", id));
        } else {
            throw new QuinotsException(String.format("Note with ID %d can't be found", id));
        }

        return baseResponse;
    }

}
