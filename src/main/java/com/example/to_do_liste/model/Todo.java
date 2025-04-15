package com.example.to_do_liste.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data   // Lombok: erzeugt Getter, Setter, equals, hashCode, toString
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

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt;        // Zeitpunkt an dem To-do gelöscht wurde, "null" bedeutet nicht gelöscht

    @ManyToOne                              // Wenn ein To-do nur zu einem Project gehört, aber ein Project mehrere To-dos enthalten kann
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToMany(mappedBy = "todos")         // Wenn ein To-do zu mehreren StudyPlans gehört und ein StudyPlan mehrere To-dos enthalten kann
    private Set<StudyPlan> studyPlans = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person owner;

                                            // enum ist ein Datentyp der besagt, dass die Variable nur einen Wert auf dieser festen Liste annehmen darf.
    public enum Status {                    //Speichert den Enum-Wert als Klartext in der DB -> keine Tippfehler, bessere Auswertung und valide Werte
        TODO,
        DOING,
        DONE
    }

}
