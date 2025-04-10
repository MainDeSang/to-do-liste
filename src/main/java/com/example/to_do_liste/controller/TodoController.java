package com.example.to_do_liste.controller;

import com.example.to_do_liste.dto.TodoDTO;
import com.example.to_do_liste.repository.TodoRepository;
import lombok.Data;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Data
@RestController
@RequestMapping("/api/users")
public class TodoController {
    private final TodoRepository todoRepository;

    @GetMapping
    public List<TodoDTO> getAllTodos() {
        return todoRepository.getAllTodos();
    }
}
