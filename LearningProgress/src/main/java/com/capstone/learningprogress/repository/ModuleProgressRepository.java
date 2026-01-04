package com.capstone.learningprogress.repository;

import com.capstone.learningprogress.entity.ModuleProgress;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ModuleProgressRepository extends JpaRepository<ModuleProgress, UUID> {

    Optional<ModuleProgress> findByUserIdAndCourseIdAndModuleId(
            UUID userId, UUID courseId, UUID moduleId);

    List<ModuleProgress> findByUserIdAndCourseId(UUID userId, UUID courseId);

    long countByUserIdAndCourseIdAndCompletedTrue(UUID userId, UUID courseId);
}
