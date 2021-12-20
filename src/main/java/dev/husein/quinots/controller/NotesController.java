package dev.husein.quinots.controller;

import dev.husein.quinots.model.BaseResponse;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.service.NoteDTO;
import dev.husein.quinots.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@RestController
@RequestMapping("/quinots/api/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create/{userId}")
    public BaseResponse createNote(@PathVariable(value = "userId") Long userId, @RequestBody NoteDTO note) {
        return noteService.createNote(userId, note.getTitle(), note.getDescription(), note.getDateTimeCreated(), note.getTags());
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
    public BaseResponse listAllNotes() {
        return noteService.listAllNotes();
    }

    @GetMapping("/search")
    public BaseResponse searchNotes(@RequestParam(value = "includingWords", required = false) String includingWords,
                                    @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Calendar fromDate,
                                    @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Calendar toDate,
                                    @RequestParam(value = "tags", required = false) String tags) {
        return noteService.searchNotes(includingWords, fromDate, toDate, tags);
    }

    @GetMapping("/list/{id}")
    @ResponseBody
    public BaseResponse getNoteById(@PathVariable("id") Long id) {
        return noteService.getNoteById(id);
    }

}

