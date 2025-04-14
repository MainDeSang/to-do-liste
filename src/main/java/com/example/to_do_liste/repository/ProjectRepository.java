package com.example.to_do_liste.repository;

import com.example.to_do_liste.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {

}
