package com.example.to_do_liste.service;

import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.model.Project;
import com.example.to_do_liste.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;

    public Project createProject(Project project) {
        if (project.getTitle() == null || project.getTitle().isEmpty()) {
            throw new IllegalArgumentException("Project title cannot be empty");
        }

        // Optional: Platzhalter für Owner – hier wäre evtl. ein echter User sinnvoll
        if (project.getOwner() == null) {
            project.setOwner(Person.builder().username("Unbekannt").build());
        }

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
