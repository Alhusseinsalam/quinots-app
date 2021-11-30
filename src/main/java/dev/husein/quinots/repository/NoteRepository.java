package dev.husein.quinots.repository;

import dev.husein.quinots.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Calendar;
import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Long> {

    @Query("SELECT n FROM Note n" +
            " WHERE (:words IS NULL OR n.description LIKE %:words% OR n.title LIKE %:words%)" +
            " AND ((:fromDate IS NULL OR n.dateTimeCreated >= STR_TO_DATE(:fromDate, '%Y-%m-%d %H:%i:%s'))" +
            " AND (:toDate IS NULL OR n.dateTimeCreated <= STR_TO_DATE(:toDate, '%Y-%m-%d %H:%i:%s')))")
    List<Note> searchNotes(@Param("words") String includingWords, @Param("fromDate") Calendar fromDate, @Param("toDate") Calendar toDate);
}
