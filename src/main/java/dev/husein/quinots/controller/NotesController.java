package dev.husein.quinots.controller;

import dev.husein.quinots.exception.QuinotsException;
import dev.husein.quinots.model.BaseJson;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.model.ResponseError;
import dev.husein.quinots.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/quinots/api/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create")
    public BaseJson createNote(@RequestBody Note note) {
        return noteService.createNote(note);
    }

    @DeleteMapping("/delete")
    public void deleteNoteById(@RequestParam("id") Long id) {
        noteService.deleteNote(id);
    }

    @PatchMapping("/update")
    public void editNote(@RequestParam("id") Long id, @RequestBody Note editedNote) {
        noteService.updateNote(id, editedNote);
    }

    @GetMapping("/list")
    public BaseJson listAllNotes() {
        return noteService.listAllNotes();
    }

    @GetMapping("/search")
    public BaseJson searchNotes(@RequestParam(value = "includingWords", required = false) String includingWords,
                                @RequestParam(value = "fromDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fromDate,
                                @RequestParam(value = "toDate", required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime toDate,
                                @RequestParam(value = "tags", required = false) String tags) {
        return noteService.searchNotes(includingWords, fromDate, toDate, tags);
    }

    @GetMapping("/list/{id}")
    @ResponseBody
    public BaseJson getNoteById(@PathVariable("id") Long id) {
        return noteService.getNoteById(id);
    }

}

