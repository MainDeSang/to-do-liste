package com.example.to_do_liste;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.HashSet;

@Entity
@Data                       //erzeugt Getter, Setter, equals, hashCode, toString
@AllArgsConstructor
@NoArgsConstructor // NoArgsConstructor muss hinzugefügt werde, da dieser in TodoMapper.java verwendet wird!
public class Todo {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private String status;      // "Done", "in progress", ...

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany(mappedBy = "todos")
    private Set<StudyPlan> studyPlans = new HashSet<>();

}
