package com.notes.repositories;

import java.util.List;

import com.notes.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import com.notes.entities.User;

public interface NotesRepo extends JpaRepository<Note, Integer>{
    List<Note> findByUser(User user);
}
