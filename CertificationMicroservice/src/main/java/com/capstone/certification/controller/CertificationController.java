package com.capstone.certification.controller;

import com.capstone.certification.entity.Certificate;
import com.capstone.certification.service.CertificatePdfService;
import com.capstone.certification.service.CertificationService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/certificates")
public class CertificationController {

    private final CertificationService service;

    private final CertificatePdfService pdfService;

    public CertificationController(
    		CertificationService service,
        CertificatePdfService pdfService
    ) {
        this.service = service;
        this.pdfService = pdfService;
    }

    @PreAuthorize("hasAnyRole('TRAINEE')")
    @PostMapping("/issue")
    public Certificate issueCertificate(
            @RequestParam UUID userId,
            @RequestParam UUID courseId) {

        return service.issueCertificate(userId, courseId);
    }
    @PreAuthorize("hasAnyRole('TRAINEE')")
    @GetMapping("/user/{userId}")
    public List<Certificate> getUserCertificates(
            @PathVariable UUID userId) {

        return service.getCertificatesByUser(userId);
    }
    
    @GetMapping("/{certificateId}/download")
    @PreAuthorize("hasAnyRole('TRAINEE')")
    public ResponseEntity<byte[]> downloadCertificate(@PathVariable UUID certificateId) {

        Certificate certificate = service.getCertificateById(certificateId);

        byte[] pdfBytes = pdfService.generateCertificatePdf(certificate);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=certificate-" + certificateId + ".pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(pdfBytes);
    }
}
