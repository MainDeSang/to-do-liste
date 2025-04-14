package com.example.to_do_liste.controller;

import com.example.to_do_liste.model.Todo;
import com.example.to_do_liste.dto.TodoDto;
import com.example.to_do_liste.repository.TodoRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController                                                                 // macht Klasse zu API-Schnittstelle
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000")                                 // Erlaubt den Zugriff vom Frontend
public class TodoController {
    private final TodoRepository todoRepository;

    public TodoController(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

    @GetMapping
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();                            // Holt alle To-dos aus der Datenbank

        return todos.stream()                                                   // Jedes To-do geht einzeln „durch den Kanal“ (stream)
                .map(this::convertToDto)                                        // Für jedes To-do wird ein TodoDto erstellt
                .toList();                                                      // Die dtos werden in einer neuen Liste gesammelt
    }

    private TodoDto convertToDto(Todo todo) {
        TodoDto todoDto = new TodoDto();
        todoDto.id = todo.getId();
        todoDto.title = todo.getTitle();
        todoDto.description = todo.getDescription();
        todoDto.startDate = todo.getStartDate();
        todoDto.endDate = todo.getEndDate();
        todoDto.status = todo.getStatus().name();               // .name() ist eine Methode der enum-Klasse und gibt den genauen Namen des Enum-Werts als String zurück
        todoDto.ownerId = todo.getOwner().getId();
        todoDto.projectId = todo.getProject() != null ? todo.getProject().getId() : null;
        return todoDto;
    }
}
