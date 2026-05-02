package com.smarthire.parser;

import java.io.File;
import java.io.IOException;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Component;

@Component
public class ResumeParser {

	public String parseResume(String filePath) {

		try (PDDocument document = Loader.loadPDF(new File(filePath))) {

			PDFTextStripper pdfTextStripper = new PDFTextStripper();

			return pdfTextStripper.getText(document);

		} catch (IOException e) {

			return "Error parsing resume: " + e.getMessage();
		}
	}
}