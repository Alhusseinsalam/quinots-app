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
@Table(name = "q_note")
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "note_id")
    @JsonProperty("noteId")
    private Long id;

    @Column(name = "title")
    @JsonProperty("title")
    private String title;

    @Column(name = "description", length = 65535, columnDefinition = "text")
    @JsonProperty("description")
    private String description;

    @Column(name = "date_time_created")
    @CreationTimestamp
    @JsonProperty("dateTimeCreated")
    private Timestamp dateTimeCreated;

    @Column(name = "tags")
    @Convert(converter = TagsConverter.class)
    @JsonProperty("tags")
    private List<String> tags;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    @JsonProperty("user")
    private User user;

    public Note(String title, String description, List<String> tags, User user) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.user = user;
    }
}
