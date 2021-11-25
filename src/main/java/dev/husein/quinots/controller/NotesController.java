package dev.husein.quinots.controller;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.husein.quinots.model.BaseJson;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/quinots/api")
public class NotesController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/test")
    public BaseJson testNote() {
        Note note = new Note();
        note.setTitle("Test Note");
        note.setDescription("Detrius peritus domus est.");
        note.setDateTimeCreated(LocalDateTime.now());
        note.setTags(new ArrayList<String>(Arrays.asList("work", "java", "future", "fun")));

        noteRepository.save(note);

        BaseJson base = new BaseJson();

        base.addNoteToList(noteRepository.findById(note.getId()).get());

        return base;
    }

    @GetMapping("/list")
    public BaseJson listAllNotes() {
        return new BaseJson(noteRepository.findAll());
    }


    @DeleteMapping("/delete")
    public void deleteNoteById(@RequestParam("id") Long id) {
        noteRepository.deleteById(id);
    }

}
