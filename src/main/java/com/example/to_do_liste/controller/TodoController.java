package com.example.to_do_liste.controller;

import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.model.Project;
import com.example.to_do_liste.model.Todo;
import com.example.to_do_liste.dto.TodoDto;
import com.example.to_do_liste.repository.PersonRepository;
import com.example.to_do_liste.repository.TodoRepository;
import com.example.to_do_liste.repository.PersonRepository;
import com.example.to_do_liste.repository.ProjectRepository;
import com.example.to_do_liste.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController                                                                 // macht Klasse zu API-Schnittstelle
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:3000")                                 // Erlaubt den Zugriff vom Frontend
public class TodoController {
    private final TodoRepository todoRepository;
    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;
    private final TodoService todoService;

    public TodoController(TodoRepository todoRepository, ProjectRepository projectRepository, PersonRepository personRepository, TodoService todoService) {
        this.todoRepository = todoRepository;
        this.projectRepository = projectRepository;
        this.personRepository = personRepository;
        this.todoService = todoService;
    }

    @GetMapping
    public List<TodoDto> getAllTodosNotDeleted() {
        return todoService.getAllNotDeleted().stream()                          // Jedes To-do geht einzeln „durch den Kanal“ (stream)
                .map(this::convertToDto)                                        // Für jedes To-do wird ein TodoDto erstellt
                .toList();                                                      // Die dtos werden in einer neuen Liste gesammelt
    }

    @GetMapping("/deleted")
    public List<TodoDto> getAllDeletedTodos() {
        return todoService.getAllDeleted().stream()
                .map(this::convertToDto)
                .toList();
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
        todoDto.projectId = todo.getProject() != null ?
                todo.getProject().getId() : null;
        return todoDto;
    }

    @PostMapping
    public TodoDto createTodo(@RequestBody TodoDto todoDto) {                   // @RequestBody sorgt dafür, dass der empfangene Text aus der Anfrage automatisch in ein Java-Objekt umgewandelt wird
        return convertToDto(todoService.createTodo(todoDto));
    }



    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteTodo(@PathVariable Long id) {
        todoService.softDeleteTodo(id);
    }

    @PutMapping("/{id}/restore")
    public TodoDto restoreTodo(@PathVariable Long id) {
        return convertToDto(todoService.restoreTodo(id));
    }
}
