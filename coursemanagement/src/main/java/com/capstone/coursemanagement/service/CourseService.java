package com.capstone.coursemanagement.service;

import com.capstone.coursemanagement.client.UserClient;
import com.capstone.coursemanagement.dto.CourseRequestDTO;
import com.capstone.coursemanagement.dto.CourseResponseDTO;
import com.capstone.coursemanagement.dto.UserDTO;
import com.capstone.coursemanagement.entity.Course;
import com.capstone.coursemanagement.repository.CourseModuleRepository;
import com.capstone.coursemanagement.repository.CourseRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CourseService {

    private final CourseRepository repository;
    private final CourseModuleRepository crp;
    private final UserClient userClient;
    public CourseService(CourseRepository repository, CourseModuleRepository crp, UserClient userClient) {
        this.repository = repository;
        this.crp=crp;
        this.userClient = userClient;
    }

    public CourseResponseDTO createCourse(CourseRequestDTO dto, UUID trainerId) {
        UserDTO trainer = userClient.getUserById(trainerId);

        if (!"TRAINER".equalsIgnoreCase(trainer.getRole())) {
            throw new RuntimeException("User is not a trainer!");
        }

        Course course = new Course();
        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setSkillLevel(dto.getSkillLevel());
        course.setDurationHours(dto.getDurationHours());
        course.setTrainerId(trainerId);

        return mapToResponse(repository.save(course));
    }
    
//    public List<CourseResponseDTO> getCoursesByTrainer(UUID id) {
//
//        List<Course> courses = repository.findByTrainerId(id);
//
//        return courses.stream()
//                .map(course -> {
//                    CourseResponseDTO dto = new CourseResponseDTO();
//                    dto.setId(course.getId());
//                    dto.setTitle(course.getTitle());
//                    dto.setDescription(course.getDescription());
//                    dto.setSkillLevel(course.getSkillLevel());
//                    dto.setDurationHours(course.getDurationHours());
//                    dto.setActive(course.getActive());
//                    return dto;
//                })
//                .toList();   // Java 16+
//    }


    public CourseResponseDTO getCourseById(UUID id) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        return mapToResponse(course);
    }

    public List<CourseResponseDTO> getAllCourses() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    public CourseResponseDTO updateCourse(UUID id, CourseRequestDTO dto) {
        Course course = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Course not found"));

        course.setTitle(dto.getTitle());
        course.setDescription(dto.getDescription());
        course.setSkillLevel(dto.getSkillLevel());
        course.setDurationHours(dto.getDurationHours());

        return mapToResponse(repository.save(course));
    }

    public void deleteCourse(UUID id) {
        repository.deleteById(id);
        System.out.println(crp.removeByCourseId(id));
    }

    public List<Course> getCoursesByTrainer(UUID trainerId) {
        // Optional: Validate trainer exists
//        UserDTO trainer = userClient.getUserById(trainerId);
//        if (trainer == null) {
//            throw new RuntimeException("Trainer not found in User Service");
//        }
        System.out.println("Trainer Role ");
//        if (!"TRAINER".equalsIgnoreCase(trainer.getRole())) {
//            throw new RuntimeException("User is not a Trainer");
//        }

        return repository.findByTrainerId(trainerId);
    }

    private CourseResponseDTO mapToResponse(Course course) {
        CourseResponseDTO dto = new CourseResponseDTO();
        dto.setId(course.getId());
        dto.setTitle(course.getTitle());
        dto.setDescription(course.getDescription());
        dto.setSkillLevel(course.getSkillLevel());
        dto.setDurationHours(course.getDurationHours());
        dto.setActive(course.getActive());
        return dto;
    }
}
