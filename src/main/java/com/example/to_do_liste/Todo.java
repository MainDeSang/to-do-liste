package com.example.to_do_liste;

import com.fasterxml.jackson.databind.DatabindException;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;

@Entity
@Data   //erzeugt Getter, Setter, equals, hashCode, toString
@AllArgsConstructor
@Builder
@NoArgsConstructor
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
