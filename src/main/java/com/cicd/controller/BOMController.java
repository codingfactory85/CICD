package com.cicd.controller;

import com.cicd.model.BOM;
import com.cicd.service.BOMService;
import com.cicd.service.OCRService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class BOMController {
    @Autowired
    private OCRService ocrService;

    @Autowired
    private BOMService bomService;

    @PostMapping("/upload")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) {
        try {
            File imageFile = new File(System.getProperty("java.io.tmpdir") + "/" + file.getOriginalFilename());
            file.transferTo(imageFile);
            String extractedText = ocrService.extractText(imageFile);
            BOM bom = bomService.parseToBOM(extractedText);
          //  String json = bomService.convertToJSON(bom);
            return ResponseEntity.ok(bom);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error processing file");
        }
    }
}

