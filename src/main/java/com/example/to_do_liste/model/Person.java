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
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private String password;

    private String email;

    // User ist der Besitzer von/hat To-dos und StudyPlans
    // Beziehung zwischen To-dos und Person (eine Person kann mehrere To-dos haben)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Todo> todos = new HashSet<>();

    // Beziehung zwischen StudyPlans und Person (eine Person kann mehrere StudyPlans haben)
    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<StudyPlan> studyPlans = new HashSet<>();
}
