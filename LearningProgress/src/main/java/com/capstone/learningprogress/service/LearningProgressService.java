package com.capstone.learningprogress.service;

import com.capstone.learningprogress.client.CourseFeignClient;
import com.capstone.learningprogress.dto.CourseProgressResponseDTO;
import com.capstone.learningprogress.dto.ModuleProgressRequestDTO;
import com.capstone.learningprogress.entity.ModuleProgress;
import com.capstone.learningprogress.repository.ModuleProgressRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

        repository.findByUserIdAndCourseIdAndModuleId(
                dto.getUserId(), dto.getCourseId(), dto.getModuleId()
        ).ifPresent(p -> {
            throw new RuntimeException("Module already completed");
        });

        ModuleProgress progress = new ModuleProgress();
        progress.setUserId(dto.getUserId());
        progress.setCourseId(dto.getCourseId());
        progress.setModuleId(dto.getModuleId());
        progress.setCompleted(true);
        progress.setCompletedAt(LocalDateTime.now());

        repository.save(progress);
    }

    public CourseProgressResponseDTO getCourseProgress(UUID userId, UUID courseId) {
    	System.out.println("courseid :  "+courseId);
        int totalModules =courseClient.getModulesByCourse(courseId).size();
        System.out.println("Total Modules :"+totalModules);

//        long completedModules =
//                repository.countByUserIdAndCourseIdAndCompletedTrue(userId, courseId);
        long completedModules =
                repository.countCompleted(
                userId.toString(),
                courseId.toString()
            );
        
        System.out.println("Completed  Modules :"+completedModules);

        double percentage =
                (totalModules == 0) ? 0 : (completedModules * 100.0) / totalModules;

        CourseProgressResponseDTO dto = new CourseProgressResponseDTO();
        dto.setTotalModules(totalModules);
        dto.setCompletedModules((int) completedModules);
        dto.setCompletionPercentage(percentage);
        dto.setEligibleForAssessment(percentage == 100.0);
        System.out.println("Percentage :"+percentage);
        return dto;
    }
}
