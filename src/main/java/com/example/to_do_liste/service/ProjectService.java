package com.example.to_do_liste.service;

import com.example.to_do_liste.dto.ProjectDTO;
import com.example.to_do_liste.mapper.ProjectMapper;
import com.example.to_do_liste.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll()
                .stream()
                .map(projectMapper::toDTO) // ✅ jetzt korrekt über Bean
                .collect(Collectors.toList());
    }

}
