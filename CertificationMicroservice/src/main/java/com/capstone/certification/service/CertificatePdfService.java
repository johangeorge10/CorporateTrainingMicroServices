package com.capstone.certification.service;


import com.capstone.certification.entity.Certificate;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class CertificatePdfService {

    public byte[] generateCertificatePdf(Certificate certificate) {

        try {
            Document document = new Document();
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, out);

            document.open();

            Font titleFont = new Font(Font.HELVETICA, 24, Font.BOLD);
            Font textFont = new Font(Font.HELVETICA, 14);

            document.add(new Paragraph("🎓 Certificate of Completion", titleFont));
            document.add(Chunk.NEWLINE);

            document.add(new Paragraph("Certificate Number: " + certificate.getCertificateNumber(), textFont));
            document.add(new Paragraph("User ID: " + certificate.getUserId(), textFont));
            document.add(new Paragraph("Course ID: " + certificate.getCourseId(), textFont));
            document.add(new Paragraph("Issued At: " + certificate.getIssuedAt(), textFont));

            document.add(Chunk.NEWLINE);
            document.add(new Paragraph("Congratulations on successfully completing the course!", textFont));

            document.close();

            return out.toByteArray();
        } catch (Exception e) {
            throw new RuntimeException("Failed to generate certificate PDF", e);
        }
    }
}
