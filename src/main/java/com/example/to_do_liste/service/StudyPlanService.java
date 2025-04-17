package com.example.to_do_liste.service;

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
        studyPlan.setTitle(studyPlan.getTitle());
    }
}
