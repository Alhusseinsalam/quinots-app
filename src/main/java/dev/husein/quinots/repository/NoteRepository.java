package dev.husein.quinots.repository;

import dev.husein.quinots.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n" +
            " WHERE (:words IS NULL OR n.description LIKE %:words% OR n.title LIKE %:words%)" +
            " OR (:fromDate IS NULL OR dateTimeCreated >= STR_TO_DATE(:fromDate, '%Y-%m-%dT%H:%i:%s'))" +
            " OR (:toDate IS NULL OR dateTimeCreated <= STR_TO_DATE(:toDate, '%Y-%m-%dT%H:%i:%s'))")
            List<Note> searchNotes(@Param("words") String includingWords, @Param("fromDate") LocalDateTime fromDate, @Param("toDate") LocalDateTime toDate);
}
