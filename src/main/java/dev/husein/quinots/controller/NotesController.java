package dev.husein.quinots.controller;

import dev.husein.quinots.exception.IllegalQueryParamException;
import dev.husein.quinots.model.BaseJson;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.model.ResponseError;
import dev.husein.quinots.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

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
                                 @RequestParam(value = "fromDate", required = false) LocalDate fromDate,
                                 @RequestParam(value = "toDate", required = false) LocalDate toDate) {
        return noteService.searchNotes(includingWords, fromDate, toDate);
    }

    @GetMapping("/list/{id}")
    public BaseJson getNoteById(@PathVariable("id") Long id) {
        return noteService.getNoteById(id);
    }

    @GetMapping("/listByTag")
    public BaseJson listByTag(@RequestParam("tag") String tag) {
        return noteService.listByTag(tag);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ResponseError> handleMissingQueryParamException(MissingServletRequestParameterException e) {
        ResponseError error = new ResponseError();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalQueryParamException.class)
    public ResponseEntity<ResponseError> handleInvalidQueryParamException(IllegalQueryParamException e) {
        ResponseError error = new ResponseError();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }
}

