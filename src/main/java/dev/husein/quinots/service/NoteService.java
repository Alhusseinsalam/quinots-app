package dev.husein.quinots.service;

import com.google.common.base.Strings;
import dev.husein.quinots.adapter.TagsConverter;
import dev.husein.quinots.exception.QuinotsException;
import dev.husein.quinots.model.BaseJson;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Service
public class NoteService {

    private NoteRepository noteRepository;

    @Autowired
    public NoteService(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    public BaseJson createNote(Note note) {
        BaseJson baseJson = new BaseJson();
        baseJson.addNoteToList(noteRepository.saveAndFlush(note));
        return baseJson;
    }

    public void deleteNote(Long id) {
        noteRepository.deleteById(id);
    }

    public void updateNote(Long id, Note newEditedNote) {
        if (noteRepository.existsById(id)) {
            Note existingNote = noteRepository.getById(id);
            // check every field to make sure you don't override the field if it is empty

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
            throw new QuinotsException();
        }
    }

    public BaseJson listAllNotes() {
        return new BaseJson(noteRepository.findAll());
    }

    public BaseJson searchNotes(String includingWords, Calendar fromDate, Calendar toDate, String tagsStr) {
        BaseJson baseJson = new BaseJson();
        List<String> tags = new ArrayList<>();

        if (!Strings.isNullOrEmpty(tagsStr)) {
            tags = Arrays.asList(tagsStr.split(TagsConverter.SEPARATOR_CHAR));
        }

        List<Note> listOfNotesWithAnyTag = noteRepository.searchNotes(includingWords, fromDate, toDate);

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


            baseJson.setNotes(notesFilteredByTag);
        } else {
            baseJson.setNotes(listOfNotesWithAnyTag);
        }

        return baseJson;
    }

    public BaseJson getNoteById(Long id) {
        BaseJson baseJson = new BaseJson();
        if (noteRepository.existsById(id)) {
            baseJson.addNoteToList(noteRepository.findById(id).orElse(null));
        } else {
            throw new QuinotsException(String.format("Note with ID %d can't be found", id));
        }

        return baseJson;
    }

}
