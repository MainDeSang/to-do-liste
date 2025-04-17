package com.example.to_do_liste.service;

import com.example.to_do_liste.model.Person;
import com.example.to_do_liste.model.StudyPlan;
import com.example.to_do_liste.repository.StudyPlanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudyPlanService {

    private final StudyPlanRepository studyPlanRepository;

    public StudyPlan createStudyPlan(StudyPlan studyPlan) {
        // Prüfen, ob StudyPlan schon vorhanden.
        if (studyPlanRepository.findById(studyPlan.getId()).isPresent()) {
            throw new IllegalArgumentException("StudyPlan already exists");
        } else if (studyPlan.getTitle() == null || studyPlan.getDescription() == null) {
            throw new IllegalArgumentException("StudyPlan title and description are required");
        } else if (studyPlan.getStartDate() == null) {
            throw new IllegalArgumentException("StudyPlan start date is required");
        }
        // Optional: Platzhalter für Owner – hier wäre evtl. ein echter User sinnvoll
        if (studyPlan.getOwner() == null) {
            studyPlan.setOwner(Person.builder().username("Unbekannt").build());
        }
        return studyPlanRepository.save(studyPlan);
    }

    public StudyPlan updateStudyPlan(StudyPlan studyPlan) {
        StudyPlan existing = studyPlanRepository.findById(studyPlan.getId())
                .orElseThrow(() -> new RuntimeException("Kein Projekt gefunden"));

        existing.setTitle(studyPlan.getTitle());
        existing.setDescription(studyPlan.getDescription());
        existing.setStartDate(studyPlan.getStartDate());

        // Todos hinzufügen oder ersetzen

    }
}
