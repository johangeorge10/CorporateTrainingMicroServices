package com.capstone.coursemanagement.aianalyzer;

import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PdfAnalyzer {
	public String extractText(MultipartFile file) {
		try {
			byte[] filebts = file.getBytes();
			try(PDDocument doc = Loader.loadPDF(filebts)){
				PDFTextStripper stripper = new PDFTextStripper();
				return stripper.getText(doc);
			}
		}
		catch (IOException e) {
            throw new RuntimeException("Failed to extract text from PDF", e);
        }
	}
}
