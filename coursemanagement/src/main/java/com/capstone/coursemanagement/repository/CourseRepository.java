package com.capstone.coursemanagement.repository;


import com.capstone.coursemanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CourseRepository extends JpaRepository<Course, UUID> {
}
