package com.capstone.assessment.controller;

import com.capstone.assessment.dto.*;
import com.capstone.assessment.entity.Assessment;
import com.capstone.assessment.entity.Question;
import com.capstone.assessment.service.AssessmentService;

import java.util.List;
import java.util.UUID;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/assessments")
public class AssessmentController {

    private final AssessmentService service;

    public AssessmentController(AssessmentService service) {
        this.service = service;
    }

    // -------- TRAINER --------
    @PreAuthorize("hasAnyRole('TRAINER')")
    @PostMapping("/course/{courseId}")
    public Assessment createAssessment(
            @PathVariable UUID courseId,
            @RequestBody CreateAssessmentDTO dto) {
        return service.createAssessment(courseId, dto);
    }
    @PreAuthorize("hasAnyRole('TRAINER')")
    @GetMapping("/course/assesments")
    public List<Assessment> cassesment() {
    	return service.cassesmment();
    }
    @PreAuthorize("hasAnyRole('TRAINER')")
    @PostMapping("/{assessmentId}/questions")
    public Question addQuestion(
            @PathVariable UUID assessmentId,
            @RequestBody QuestionCreateDTO dto) {
        return service.addQuestion(assessmentId, dto);
    }
    @PreAuthorize("hasAnyRole('TRAINER')")
    @DeleteMapping("/{assessmentId}")
    public void deletequestion(@PathVariable UUID qid) {
    	service.deleteQuestion(qid);
    }

    // -------- USER --------
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @GetMapping("/{assessmentId}/questions/user")
    public List<QuestionUserViewDTO> getQuestionsForUser(
            @PathVariable UUID assessmentId) {
        return service.getQuestionsForUser(assessmentId);
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @PostMapping("/attempt")
    public AssessmentResultDTO attemptExam(
            @RequestBody AssessmentAttemptRequestDTO dto) {
        return service.attemptExam(dto);
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @GetMapping("/attempt/status")
    public AssessmentAttemptStatusDTO getAttemptStatus(
            @RequestParam UUID userId,
            @RequestParam UUID courseId) {

        return service.getAttemptStatus(userId, courseId);
    }

    //IDK May be Not Reqired after frontend
    @PreAuthorize("hasAnyRole('ADMIN')")
    @PutMapping("/editcourseidassid/{courseId}/{assesmentId}")
    public String editcourseId(@PathVariable UUID courseId,@PathVariable UUID assesmentId) {
    	return service.EditCourseId(courseId,assesmentId);
    }
}

