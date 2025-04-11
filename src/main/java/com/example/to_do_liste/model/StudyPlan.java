package com.example.to_do_liste.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@Builder
@NoArgsConstructor
public class StudyPlan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "studyplan_todo",
            joinColumns = @JoinColumn(name = "studyplan_id"),
            inverseJoinColumns = @JoinColumn(name = "todo_id")
    )
    private Set<Todo> todos = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;

}
