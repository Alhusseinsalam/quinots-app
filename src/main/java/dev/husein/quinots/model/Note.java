package dev.husein.quinots.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.husein.quinots.adapter.TagsConverter;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "NOTE")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "noteId")
    @JsonProperty("noteId")
    private Long id;

    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @Column(name = "description", length = 65535, columnDefinition = "text")
    @JsonProperty("description")
    private String description;

    @Column(name = "dateTimeCreated")
    @JsonProperty("dateTimeCreated")
    private LocalDateTime dateTimeCreated;

    @Column(name = "tags")
    @Convert(converter = TagsConverter.class)
    @JsonProperty("tags")
    private List<String> tags;
}
