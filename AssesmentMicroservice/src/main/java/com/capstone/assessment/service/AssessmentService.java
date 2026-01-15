package com.capstone.assessment.service;

import com.capstone.assessment.client.EnrollmentFeignClient;
import com.capstone.assessment.client.LearningProgressFeignClient;
import com.capstone.assessment.dto.*;
import com.capstone.assessment.entity.*;
import com.capstone.assessment.repository.*;
import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AssessmentService {

    private final AssessmentRepository assessmentRepo;
    private final QuestionRepository questionRepo;
    private final AssessmentAttemptRepository attemptRepo;
    private final LearningProgressFeignClient progressClient;
    private final EnrollmentFeignClient enrollmentClient;
    
    public AssessmentService(
            AssessmentRepository assessmentRepo,
            QuestionRepository questionRepo,
            AssessmentAttemptRepository attemptRepo,
            LearningProgressFeignClient progressClient,
            EnrollmentFeignClient enrollmentClient
    ) {
        this.assessmentRepo = assessmentRepo;
        this.questionRepo = questionRepo;
        this.attemptRepo = attemptRepo;
        this.progressClient = progressClient;
        this.enrollmentClient = enrollmentClient;
    }

    // -------- TRAINER --------

    public Assessment createAssessment(UUID courseId, CreateAssessmentDTO dto) {
        assessmentRepo.findByCourseId(courseId)
                .ifPresent(a -> { throw new RuntimeException("Assessment already exists"); });

        Assessment assessment = new Assessment();
        assessment.setCourseId(courseId);
        assessment.setPassPercentage(
                dto.getPassPercentage() != null ? dto.getPassPercentage() : 70
        );

        return assessmentRepo.save(assessment);
    }
 // In AssessmentService.java
    public Optional<Assessment> findAssessmentByCourseId(UUID courseId) {
        return assessmentRepo.findByCourseId(courseId);
    }

    public List<Question> findQuestionsByAssessmentId(UUID assessmentId) {
        return questionRepo.findByAssessmentId(assessmentId);
    }
    public void deleteQuestion(UUID questionId) {
        // Optional: Check existence first to give a better error message
        if (!questionRepo.existsById(questionId)) {
            throw new RuntimeException("Question not found with ID: " + questionId);
        }
        questionRepo.deleteById(questionId);
    }
    public List<Assessment> cassesmment() {
    	return assessmentRepo.findAll();
    }

    public Question addQuestion(UUID assessmentId, QuestionCreateDTO dto) {
        Assessment assessment = assessmentRepo.findById(assessmentId)
                .orElseThrow(() -> new RuntimeException("Assessment not found"));

        Question q = new Question();
        q.setAssessmentId(assessment.getId());
        q.setQuestionText(dto.getQuestionText());
        q.setOptionA(dto.getOptionA());
        q.setOptionB(dto.getOptionB());
        q.setOptionC(dto.getOptionC());
        q.setOptionD(dto.getOptionD());
        q.setCorrectOption(dto.getCorrectOption());

        return questionRepo.save(q);
    }

    // -------- USER --------

    public List<QuestionUserViewDTO> getQuestionsForUser(UUID assessmentId) {
        return questionRepo.findByAssessmentId(assessmentId)
                .stream()
                .map(q -> {
                    QuestionUserViewDTO dto = new QuestionUserViewDTO();
                    dto.setId(q.getId());
                    dto.setQuestionText(q.getQuestionText());
                    dto.setOptionA(q.getOptionA());
                    dto.setOptionB(q.getOptionB());
                    dto.setOptionC(q.getOptionC());
                    dto.setOptionD(q.getOptionD());
                    return dto;
                }).toList();
    }

   public AssessmentResultDTO attemptExam(AssessmentAttemptRequestDTO dto) {

    Assessment assessment = assessmentRepo.findById(dto.getAssessmentId())
            .orElseThrow(() -> new RuntimeException("Assessment not found"));

    // ✅ Eligibility check
    CourseProgressResponseDTO progress = progressClient.getCourseProgress(
            assessment.getCourseId(), dto.getUserId());

    if (progress.getCompletionPercentage() < 100) {
        throw new RuntimeException("Course not fully completed");
    }

    // ✅ Prevent multiple attempts
    attemptRepo.findByUserIdAndAssessmentId(dto.getUserId(), dto.getAssessmentId())
            .ifPresent(a -> {
                throw new RuntimeException("Assessment already attempted");
            });

    // ✅ Load questions
    List<Question> questions = questionRepo.findByAssessmentId(dto.getAssessmentId());

    if (questions.isEmpty()) {
        throw new RuntimeException("No questions found for assessment");
    }

    // ✅ Evaluate answers
    int correct = 0;
    for (Question q : questions) {
        String userAnswer = dto.getAnswers().get(q.getId());
        if (userAnswer != null &&
            q.getCorrectOption().equalsIgnoreCase(userAnswer)) {
            correct++;
        }
    }

    int score = (int) ((correct * 100.0) / questions.size());
    boolean passed = score >= assessment.getPassPercentage();

    // ✅ Persist attempt FIRST
    AssessmentAttempt attempt = new AssessmentAttempt();
    attempt.setUserId(dto.getUserId());
    attempt.setAssessmentId(dto.getAssessmentId());
    attempt.setScorePercentage(score);
    attempt.setPassed(passed);
    attempt.setAttemptedAt(LocalDateTime.now());

    attemptRepo.save(attempt);

    // ✅ Mark course completed regardless of pass/fail
    try {
        enrollmentClient.markCourseCompleted(
                dto.getUserId(),
                assessment.getCourseId()
        );
        System.out.println("✅ Course marked as COMPLETED in Enrollment Service");
    } catch (Exception ex) {
        System.err.println("❌ Failed to update course completion: " + ex.getMessage());
        // intentionally NOT throwing exception → assessment result must still succeed
    }

    // ✅ Build response
    AssessmentResultDTO result = new AssessmentResultDTO();
    result.setScorePercentage(score);
    result.setPassed(passed);
    return result;
}

    public AssessmentAttemptStatusDTO getAttemptStatus(UUID userId, UUID courseId) {
    	
    	Assessment ob=assessmentRepo.findByCourseId(courseId).orElseThrow( ()->new RuntimeException("No Course Found"));
    	
    	
        return attemptRepo
            .findByUserIdAndAssessmentId(userId, ob.getId())
            .map(attempt -> new AssessmentAttemptStatusDTO(
                    true,
                    attempt.getPassed(),
                    attempt.getScorePercentage(),
                    attempt.getAttemptedAt(),
                    attempt.getAssessmentId()
            ))
            .orElse(new AssessmentAttemptStatusDTO(false, false, null, null, null));
    }
    
    //Something fishy 
    public String EditCourseId( UUID courseId, UUID assesmentId) {
    	Assessment assessment = assessmentRepo.findById(assesmentId).orElseThrow(() -> new RuntimeException("NotFound"));
    	assessment.setCourseId(courseId);
    	assessmentRepo.save(assessment);
    	return "success";
    }
}

