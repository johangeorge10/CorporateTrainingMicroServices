package com.capstone.certification.repository;

import com.capstone.certification.entity.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CertificateRepository extends JpaRepository<Certificate, UUID> {

    boolean existsByUserIdAndCourseId(UUID userId, UUID courseId);

    List<Certificate> findByUserId(UUID userId);
}
