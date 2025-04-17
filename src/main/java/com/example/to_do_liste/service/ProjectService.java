package com.example.to_do_liste.service;

import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.model.Project;
import com.example.to_do_liste.model.Todo;
import com.example.to_do_liste.repository.PersonRepository;
import com.example.to_do_liste.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PersonRepository personRepository;

    public Project createProject(Project project) {
        // Prüfung ob Title vergeben wurde, andernfalls wird eine Fehlermeldung geworfen
        if (project.getTitle() == null || project.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Project title cannot be empty");
        }
        project.setTitle(project.getTitle());
        project.setDescription(project.getDescription());
        project.setOwner(Person.builder().build());
        project.setTodos(project.getTodos());
        return projectRepository.save(project);
    }

    public Project updateProject(Project project) {
        // Projekt aus der Datenbank abrufen
        Project updatedProject = projectRepository.findById(project.getId())
                .orElseThrow(
                        () -> new RuntimeException("Kein Projekt gefunden"));


        // Felder aktualisieren
        project.setTitle(updatedProject.getTitle());
        project.setDescription(updatedProject.getDescription());
        project.getTodos().add(new Todo());
        // Aktualisiertes Projekte speichern
        return projectRepository.save(updatedProject);
    }
}
