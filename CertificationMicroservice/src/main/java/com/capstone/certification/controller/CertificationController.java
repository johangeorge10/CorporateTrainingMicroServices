package com.capstone.certification.controller;

import com.capstone.certification.entity.Certificate;
import com.capstone.certification.service.CertificationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificates")
public class CertificationController {

    private final CertificationService service;

    public CertificationController(CertificationService service) {
        this.service = service;
    }

    @PostMapping("/issue")
    public Certificate issueCertificate(
            @RequestParam UUID userId,
            @RequestParam UUID courseId) {

        return service.issueCertificate(userId, courseId);
    }

    @GetMapping("/user/{userId}")
    public List<Certificate> getUserCertificates(
            @PathVariable UUID userId) {

        return service.getCertificatesByUser(userId);
    }
}
