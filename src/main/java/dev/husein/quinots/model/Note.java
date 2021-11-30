package dev.husein.quinots.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.husein.quinots.adapter.TagsConverter;
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
    @CreationTimestamp
    @JsonProperty("dateTimeCreated")
    private Timestamp dateTimeCreated;

    @Column(name = "tags")
    @Convert(converter = TagsConverter.class)
    @JsonProperty("tags")
    private List<String> tags;
}
