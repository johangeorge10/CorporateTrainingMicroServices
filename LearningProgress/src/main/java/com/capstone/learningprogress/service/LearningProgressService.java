package com.capstone.learningprogress.service;

import com.capstone.learningprogress.client.CourseFeignClient;
import com.capstone.learningprogress.dto.CourseProgressResponseDTO;
import com.capstone.learningprogress.dto.ModuleProgressRequestDTO;
import com.capstone.learningprogress.entity.ModuleProgress;
import com.capstone.learningprogress.repository.ModuleProgressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
//import java.util.List;
import java.util.UUID;

@Service
public class LearningProgressService {

    private final ModuleProgressRepository repository;
    private final CourseFeignClient courseClient;

    public LearningProgressService(ModuleProgressRepository repository,
                                   CourseFeignClient courseClient) {
        this.repository = repository;
        this.courseClient = courseClient;
    }

    public void markModuleCompleted(ModuleProgressRequestDTO dto) {

    boolean alreadyCompleted =
            repository.findByUserIdAndCourseIdAndModuleId(
                    dto.getUserId(),
                    dto.getCourseId(),
                    dto.getModuleId()
            ).isPresent();

    if (alreadyCompleted) {
        // Idempotent: already exists → nothing to do
        return;
    }

    ModuleProgress progress = new ModuleProgress();
    progress.setUserId(dto.getUserId());
    progress.setCourseId(dto.getCourseId());
    progress.setModuleId(dto.getModuleId());
    progress.setCompleted(true);
    progress.setCompletedAt(LocalDateTime.now());

    repository.save(progress);
}


    public CourseProgressResponseDTO getCourseProgress(UUID userId, UUID courseId) {

        int totalModules = courseClient.getModulesByCourse(courseId).size();

        List<ModuleProgress> completedEntities =
                repository.findByUserIdAndCourseIdAndCompletedTrue(userId, courseId);

        int completedModules = completedEntities.size();

        List<UUID> completedModuleIds =
                completedEntities.stream()
                        .map(ModuleProgress::getModuleId)
                        .toList();

        double percentage =
                (totalModules == 0)
                        ? 0
                        : (completedModules * 100.0) / totalModules;

        CourseProgressResponseDTO dto = new CourseProgressResponseDTO();
        dto.setTotalModules(totalModules);
        dto.setCompletedModules(completedModules);
        dto.setCompletedModuleIds(completedModuleIds);
        dto.setCompletionPercentage(percentage);
        dto.setEligibleForAssessment(percentage == 100.0);

        return dto;
    }


}
