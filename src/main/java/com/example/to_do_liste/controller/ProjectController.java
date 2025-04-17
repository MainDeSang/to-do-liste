package com.example.to_do_liste.controller;

import com.example.to_do_liste.dto.ProjectDto;
import com.example.to_do_liste.model.Project;
import com.example.to_do_liste.repository.ProjectRepository;
import com.example.to_do_liste.service.ProjectService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/projects")
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
@Builder
public class ProjectController {

    private final ProjectRepository projectRepository;
    private final ProjectService projectService;

    @GetMapping
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }

    @PostMapping
    public Project createProject(@RequestBody ProjectDto projectDto) {
        return projectService.createProject(projectDto);
    }

    @DeleteMapping
    public Project deleteProject(@RequestBody Project project) {
        projectRepository.delete(project);
        return project;
    }

    @PutMapping
    public Project updateProject(@RequestBody Project project) {
        return projectService.updateProject(project);
    }

}
