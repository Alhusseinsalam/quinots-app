package dev.husein.quinots.controller;

import dev.husein.quinots.dto.NoteDTO;
import dev.husein.quinots.model.BaseResponse;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Calendar;

@RestController
@RequestMapping("/api/notes")
public class NotesController {

    @Autowired
    private NoteService noteService;

    @PostMapping("/create")
    public BaseResponse createNote(@RequestBody NoteDTO note, Principal principal) {
        return noteService.createNote(principal.getName(), note.getTitle(), note.getDescription(), note.getTags());
    }

    @DeleteMapping("/delete")
    public void deleteNoteById(@RequestParam("id") Long id, Principal principal) {
        noteService.deleteNote(principal.getName(), id);
    }

    @PatchMapping("/update")
    public void editNote(@RequestParam("id") Long id, @RequestBody NoteDTO editedNote, Principal principal) {
        noteService.updateNote(principal.getName(), id, editedNote);
    }

    @GetMapping("/list")
    public BaseResponse listNotesForUser(Principal principal) {
        return noteService.listNotesForUser(principal.getName());
    }

    @GetMapping("/search")
    public BaseResponse searchNotes(@RequestParam(value = "includingWords", required = false) String includingWords,
                                    @RequestParam(value = "fromDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Calendar fromDate,
                                    @RequestParam(value = "toDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") Calendar toDate,
                                    @RequestParam(value = "tags", required = false) String tags,
                                    Principal principal) {
        return noteService.searchNotes(principal.getName(), includingWords, fromDate, toDate, tags);
    }

    @GetMapping("/list/{id}")
    @ResponseBody
    public BaseResponse getNoteById(@PathVariable("id") Long id, Principal principal) {
        return noteService.getNoteById(principal.getName(), id);
    }
}

