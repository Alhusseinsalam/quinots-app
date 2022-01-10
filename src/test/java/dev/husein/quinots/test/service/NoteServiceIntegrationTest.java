package dev.husein.quinots.test.service;

import dev.husein.quinots.model.BaseResponse;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.service.NoteService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class NoteServiceIntegrationTest {

    @Autowired
    private NoteService noteService;

    @Test
    public void testAddNote() {
        Note note = new Note();
        note.setTitle("TEST NOTE");
        note.setDescription("TEST NOTE DESCRIPTION");
        note.setTags(new ArrayList<>(Arrays.asList("tech", "math")));
        BaseResponse response = noteService.createNote("admin", note.getTitle(), note.getDescription(), note.getTags());
        assertNotNull(response);
        assertNotNull(response.getNotes());
        assertEquals(1, response.getSize());

    }
}
