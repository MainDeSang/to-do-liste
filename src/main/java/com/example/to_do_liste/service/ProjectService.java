package com.example.to_do_liste.service;

import com.example.to_do_liste.dto.ProjectDto;
import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.model.Project;
import com.example.to_do_liste.model.Todo;
import com.example.to_do_liste.repository.PersonRepository;
import com.example.to_do_liste.repository.ProjectRepository;
import com.example.to_do_liste.repository.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;
    private final TodoRepository todoRepository;

    public Project createProject(ProjectDto dto) {
        if (dto.getTitle() == null || dto.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Project title cannot be empty");
        }

        // Echten Owner laden
        Person owner = personRepository.findById(dto.getOwnerId())
                .orElseThrow(() -> new IllegalArgumentException("Owner not found"));

        // Todos anhand der IDs laden
        List<Todo> todos = new ArrayList<>();
        if (dto.getTodoIds() != null) {
            todos = todoRepository.findAllById(dto.getTodoIds());
        }

        // Neues Project erstellen
        Project project = Project.builder()
                .title(dto.getTitle())
                .description(dto.getDescription())
                .owner(owner)
                .todos(new HashSet<>(todos))
                .build();

        return projectRepository.save(project);
    }


    public Project updateProject(Project project) {
        Project existing = projectRepository.findById(project.getId())
                .orElseThrow(() -> new RuntimeException("Kein Projekt gefunden"));

        // Nur Felder aktualisieren, die du möchtest
        existing.setTitle(project.getTitle());
        existing.setDescription(project.getDescription());

        // Beispiel: Todo's ersetzen oder hinzufügen
        if (project.getTodos() != null) {
            existing.getTodos().clear();
            existing.getTodos().addAll(project.getTodos());
        }

        return projectRepository.save(existing);
    }


}
