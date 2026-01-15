package com.capstone.certification.service;

import com.capstone.certification.client.AssessmentClient;
import com.capstone.certification.dto.AssessmentAttemptStatusDTO;
import com.capstone.certification.entity.Certificate;
import com.capstone.certification.repository.CertificateRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class CertificationService {

    private final CertificateRepository repository;
    private final AssessmentClient assessmentClient;
//    private final CertificatePdfService pdfService;
    
    public CertificationService(
            CertificateRepository repository,
            AssessmentClient assessmentClient
//            ,CertificatePdfService pdfService
            ) {
        this.repository = repository;
        this.assessmentClient = assessmentClient;
//        this.pdfService = pdfService;
    }

    public Certificate issueCertificate(
            UUID userId,
            UUID courseId) {

        // Prevent duplicate certificate
        if (repository.existsByUserIdAndCourseId(userId, courseId)) {
            throw new RuntimeException("Certificate already issued for this course");
        }

        // Call Assessment Service
        AssessmentAttemptStatusDTO status =
                assessmentClient.getAttemptStatus(userId, courseId);

        if (!status.isAttempted()) {
            throw new RuntimeException("Assessment not attempted");
        }

        if (!status.isPassed()) {
            throw new RuntimeException("Assessment not passed");
        }

        Certificate certificate = new Certificate();
        certificate.setUserId(userId);
        certificate.setCourseId(courseId);
        certificate.setAssessmentId(status.getAssessmentId());
        certificate.setIssuedAt(LocalDateTime.now());
        certificate.setCertificateNumber(UUID.randomUUID().toString());

        return repository.save(certificate);
    }

    public List<Certificate> getCertificatesByUser(UUID userId) {
        return repository.findByUserId(userId);
    }
    
    public Certificate getCertificateById(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Certificate not found"));
    }

}
