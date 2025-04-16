package com.example.to_do_liste.service;

import com.example.to_do_liste.dto.TodoDto;
import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.model.Project;
import com.example.to_do_liste.model.Todo;
import com.example.to_do_liste.repository.PersonRepository;
import com.example.to_do_liste.repository.ProjectRepository;
import com.example.to_do_liste.repository.TodoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TodoService {
    private final TodoRepository todoRepository;
    private final PersonRepository personRepository;
    private final ProjectRepository projectRepository;


    public TodoService(TodoRepository todoRepository, PersonRepository personRepository, ProjectRepository projectRepository) {
        this.todoRepository = todoRepository;
        this.personRepository = personRepository;
        this.projectRepository = projectRepository;
    }

    public List<Todo> getAllNotDeleted() {
        List<Todo> todos = todoRepository.findAll();                            // Holt alle To-dos aus der Datenbank
        return todos.stream()                                                   // Jedes To-do geht einzeln „durch den Kanal“ (stream)
                .filter(todo -> todo.getDeletedAt() == null)              // nur To-dos die noch nicht gelöscht wurden
                .toList();                                                      // Die dtos werden in einer neuen Liste gesammelt
    }

    public List<Todo> getAllDeleted() {
        return todoRepository.findAll().stream()
                .filter(todo -> todo.getDeletedAt() != null)        // Nur To-dos, die gelöscht wurden
                .toList();
    }

    public Todo createTodo(TodoDto todoDto) {
        Person owner = personRepository.findById(todoDto.ownerId)
                .orElseThrow(
                        () -> new RuntimeException("Person nicht gefunden"));
        Project project = null;
        if (todoDto.projectId != null) {
            project = projectRepository.findById(todoDto.projectId)
                    .orElseThrow(
                            () -> new RuntimeException("Projekt nicht gefunden"));
        }

        Todo todo = Todo.builder()
                .title(todoDto.title)
                .description(todoDto.description)
                .startDate(todoDto.startDate)
                .endDate(todoDto.endDate)
                .status(Todo.Status.valueOf(todoDto.status))
                .owner(owner)
                .project(project)
                .build();

        return todoRepository.save(todo);
    }

    public Todo updateTodo(Long id, TodoDto todoDto) {
        // To-do aus der Datenbank abrufen
        Todo todo = todoRepository.findById(id)
                .orElseThrow(
                        () -> new RuntimeException("Kein Todo gefunden"));
        // Besitzer des To-dos aus der Datenbank holen
        Person owner = personRepository.findById(todoDto.ownerId)
                .orElseThrow(() -> new RuntimeException("Person nicht gefunden"));

        //Projekt laden, falls zugeordnet
        Project project = null;
        if (todoDto.projectId != null) {
            project = projectRepository.findById(todoDto.projectId)
                    .orElseThrow(
                            () -> new RuntimeException("Projekt nicht gefunden"));
        }
        // Felder aktualisieren
        todo.setTitle(todoDto.title);
        todo.setDescription(todoDto.description);
        todo.setStartDate(todoDto.startDate);
        todo.setEndDate(todoDto.endDate);
        todo.setStatus(Todo.Status.valueOf(todoDto.status));
        todo.setProject(project);
        todo.setOwner(owner);

        // Aktualisiertes To-do speichern
        return todoRepository.save(todo);
    }

    public void softDeleteTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("To-do nicht gefunden"));

        todo.setDeletedAt(LocalDateTime.now());
        todoRepository.save(todo);
    }

    public Todo restoreTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("To-do nicht gefunden"));

        todo.setDeletedAt(null);                                                        // To-do wieder aktiv machen
        todoRepository.save(todo);

        return todo;
    }
}
