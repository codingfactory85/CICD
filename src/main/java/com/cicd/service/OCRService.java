package com.cicd.service;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.springframework.stereotype.Service;
import java.io.File;

@Service
public class OCRService {
    public String extractText(File imageFile) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("C:\\Users\\ramka\\AppData\\Local\\Programs\\Tesseract-OCR\\tessdata"); // Update this path
        tesseract.setLanguage("eng");
        try {
            return tesseract.doOCR(imageFile);
        } catch (TesseractException e) {
            e.printStackTrace();
            return null;
        }
    }
}
