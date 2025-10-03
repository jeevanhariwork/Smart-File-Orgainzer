package com.example.organizer.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class FileOrganizerService {

    public List<String> organizeFiles(String folderPath) {
        List<String> movedFiles = new ArrayList<>();

        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            throw new RuntimeException("Invalid folder path: " + folderPath);
        }

        File[] files = folder.listFiles();
        if (files == null)
            return movedFiles;

        for (File file : files) {
            if (file.isFile()) {
                String extension = getFileExtension(file.getName());
                File subFolder = new File(folderPath + File.separator + extension);

                if (!subFolder.exists()) {
                    subFolder.mkdir();
                }

                try {
                    Path targetPath = subFolder.toPath().resolve(file.getName());
                    Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                    movedFiles.add(file.getName() + " -> " + subFolder.getName());
                } catch (Exception e) {
                    movedFiles.add("Failed: " + file.getName());
                }
            }
        }
        return movedFiles;
    }

    private String getFileExtension(String fileName) {
        int lastDot = fileName.lastIndexOf('.');
        if (lastDot == -1)
            return "others";
        return fileName.substring(lastDot + 1).toLowerCase();
    }
}