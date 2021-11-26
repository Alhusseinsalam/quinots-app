package dev.husein.quinots.service;

import com.google.common.base.Strings;
import dev.husein.quinots.exception.IllegalQueryParamException;
import dev.husein.quinots.model.BaseJson;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

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
            throw new IllegalQueryParamException();
        }
    }

    public BaseJson listAllNotes() {
        return new BaseJson(noteRepository.findAll());
    }

    public BaseJson searchNotes(String includingWords, LocalDate fromDate, LocalDate toDate) {
        BaseJson baseJson = new BaseJson();
        // TODO:

        return baseJson;
    }

    public BaseJson getNoteById(Long id) {
        BaseJson baseJson = new BaseJson();
        if (noteRepository.existsById(id)) {
            baseJson.addNoteToList(noteRepository.findById(id).get());
        } else {
            // this is wrong should be changed to another type of exception
            throw new IllegalQueryParamException();
        }

        return baseJson;
    }

    public BaseJson listByTag(String tag) {
        BaseJson baseJson = new BaseJson();
        // TODO:

        return baseJson;
    }

}
