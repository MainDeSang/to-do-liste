package com.example.to_do_liste.repository;

import com.example.to_do_liste.Todo;
import com.example.to_do_liste.dto.TodoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TodoRepository extends JpaRepository<Todo, Long> {
    List<TodoDTO> getAllTodos();
}
