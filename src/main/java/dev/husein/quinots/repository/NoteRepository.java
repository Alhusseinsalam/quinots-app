package dev.husein.quinots.repository;

import dev.husein.quinots.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {
}
