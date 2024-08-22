package com.synex.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class FileInputOutputController {
    public static final String UPLOAD_DIR = "/Users/gaurikumari/Desktop/Documents/";

    @PostMapping("/upload")
    public ResponseEntity<List<String>> handleFileUpload(@RequestParam("files") MultipartFile[] files) {
        List<String> fileNames = new ArrayList<>();

        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                continue;
            }

            try {
                byte[] bytes = file.getBytes();
                Path path = Paths.get(UPLOAD_DIR + file.getOriginalFilename());
                Files.write(path, bytes);
                fileNames.add(file.getOriginalFilename());
                System.out.println("Uploaded file: " + path.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (fileNames.isEmpty()) {
            return ResponseEntity.badRequest().body(null);
        }

        return ResponseEntity.ok().body(fileNames);
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadFile(@RequestParam String fileName) {
        Path filePath = Paths.get(UPLOAD_DIR + fileName);
        Resource resource = null;
        try {
            resource = new UrlResource(filePath.toUri());
            System.out.println("File path: " + filePath.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        if (resource != null && resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileName + "\"")
                    .body(resource);
        } else {
            System.out.println("File not found: " + fileName);
            return ResponseEntity.notFound().build();
        }
    }
}
