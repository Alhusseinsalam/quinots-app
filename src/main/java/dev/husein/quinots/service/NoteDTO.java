package dev.husein.quinots.service;


import com.fasterxml.jackson.annotation.JsonProperty;
import dev.husein.quinots.adapter.TagsConverter;
import dev.husein.quinots.model.User;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class NoteDTO {
    @JsonProperty("noteId")
    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("dateTimeCreated")
    private Timestamp dateTimeCreated;

    @JsonProperty("tags")
    private List<String> tags;
}
