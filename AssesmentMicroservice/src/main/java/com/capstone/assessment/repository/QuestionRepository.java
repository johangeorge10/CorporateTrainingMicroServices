package com.capstone.assessment.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.assessment.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    List<Question> findByAssessmentId(UUID assessmentId);
}