package com.example.to_do_liste.controller;

import com.example.to_do_liste.model.Project;
import com.example.to_do_liste.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectRepository projectRepository;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project createProject(@RequestBody Project project) {
        return projectRepository.save(project);
    }

    @DeleteMapping
    public Project deleteProject(@RequestBody Project project) {
        projectRepository.delete(project);
        return project;
    }

    @PutMapping
    public Project updateProject(@RequestBody Project project) {
        List<Project> projects = projectRepository.findAll();
        for (Project p : projects) {
            if (p.getId().equals(project.getId())) {
                return projectRepository.save(project);
            }
        }
    return projectRepository.save(project);}
}
