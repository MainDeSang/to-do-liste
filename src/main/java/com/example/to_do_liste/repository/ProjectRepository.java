package com.example.to_do_liste.repository;

import com.example.to_do_liste.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
//Repository für Project
public interface ProjectRepository extends JpaRepository<Project, Long> {
    public Project findByTitle(String title);
}
