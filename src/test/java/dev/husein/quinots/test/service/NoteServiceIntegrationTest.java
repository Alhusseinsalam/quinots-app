package dev.husein.quinots.test.service;

import dev.husein.quinots.model.BaseResponse;
import dev.husein.quinots.model.Note;
import dev.husein.quinots.service.NoteService;
import dev.husein.quinots.service.UserService;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("dev")
public class NoteServiceIntegrationTest {

    @Autowired
    private NoteService noteService;


    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Test
    @Order(1)
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

    @Test
    @Order(2)
    public void listNotes() {
        BaseResponse response = noteService.listNotesForUser("user1");
        assertEquals(1, response.getSize());

        response = noteService.listNotesForUser("admin");
        assertEquals(2, response.getSize());
    }
}
