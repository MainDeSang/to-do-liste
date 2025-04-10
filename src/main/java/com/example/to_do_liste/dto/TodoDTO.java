package com.example.to_do_liste.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;


//DTO for class Todo.java, must have the same attributes
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TodoDTO {
    private Long id;
    private String title;
    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String status;
    private Project project;
    private Set<StudyPlan> studyPlans = new HashSet<>();
}
