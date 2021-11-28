package dev.husein.quinots.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@ToString
public class BaseJson {
    @JsonProperty("dataSize")
    private Integer size;

    @JsonProperty("notes")
    @Getter
    @Setter
    private List<Note> notes;

    public BaseJson() {
        size = 0;
        this.notes = new ArrayList<>();
    }

    public BaseJson(List<Note> notes) {
        super();
        this.notes = notes;
    }

    public void addNoteToList(Note note) {
        this.notes.add(note);
    }

    public Integer getSize() {
        return this.notes.size();
    }
}
