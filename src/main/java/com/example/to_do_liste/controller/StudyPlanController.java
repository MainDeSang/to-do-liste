package com.example.to_do_liste.controller;

import com.example.to_do_liste.model.StudyPlan;
import com.example.to_do_liste.repository.StudyPlanRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/studyplans")
@CrossOrigin(origins = "http://localhost:3000")
@Builder
@RequiredArgsConstructor
public class StudyPlanController {

    private final StudyPlanRepository studyPlanRepository;

    @GetMapping
    public List<StudyPlan> getStudyPlans() {
        return studyPlanRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<StudyPlan> addStudyPlan(@RequestBody StudyPlan studyPlan) {
        return ResponseEntity.ok(studyPlanRepository.save(studyPlan));
    }

    @DeleteMapping
    public ResponseEntity<StudyPlan> deleteStudyPlan(@RequestBody StudyPlan studyPlan) {
        studyPlanRepository.delete(studyPlan);
        return ResponseEntity.ok(studyPlan);
    }

    @PutMapping
    public ResponseEntity<StudyPlan> updateStudyPlan(@RequestBody StudyPlan studyPlan) {
        List<StudyPlan> studyPlans = studyPlanRepository.findAll();
        for (StudyPlan plan : studyPlans) {
            if (plan.getId().equals(studyPlan.getId())) {
                return ResponseEntity.ok(studyPlanRepository.save(plan));
            }
        }
        return ResponseEntity.ok(studyPlanRepository.save(studyPlan));
    }
}
