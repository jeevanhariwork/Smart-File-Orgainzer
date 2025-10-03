package com.example.organizer.controller;

import org.springframework.web.bind.annotation.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;

@RestController
@RequestMapping("/api")
public class OrganizerController {

    // To store last moved files for undo
    private Map<String, List<Path>> lastOperation = new HashMap<>();

    @GetMapping("/hello")
    public String hello() {
        return "Hello World";
    }

    // ✅ Organize endpoint with optional fileTypes filter
    @PostMapping("/organize")
    public Map<String, Object> organizeFiles(@RequestParam String folderPath,
            @RequestParam(required = false) List<String> fileTypes) throws IOException {
        File folder = new File(folderPath);
        if (!folder.exists() || !folder.isDirectory()) {
            return Map.of("error", "Invalid folder path");
        }

        File[] files = folder.listFiles();
        if (files == null) {
            return Map.of("error", "No files found in folder");
        }

        int totalBefore = files.length;
        Map<String, Integer> counts = new HashMap<>();
        List<Path> movedFiles = new ArrayList<>();

        for (File file : files) {
            if (file.isFile()) {
                String ext = getFileExtension(file.getName());

                // If fileTypes filter is provided → skip others
                if (fileTypes != null && !fileTypes.contains(ext)) {
                    continue;
                }

                Path targetDir = Paths.get(folderPath, ext);
                if (!Files.exists(targetDir)) {
                    Files.createDirectory(targetDir);
                }

                Path targetPath = targetDir.resolve(file.getName());
                Files.move(file.toPath(), targetPath, StandardCopyOption.REPLACE_EXISTING);
                movedFiles.add(targetPath);

                counts.put(ext, counts.getOrDefault(ext, 0) + 1);
            }
        }

        // Save last moved files for undo
        lastOperation.put(folderPath, movedFiles);

        return Map.of(
                "message", "Files organized successfully",
                "totalBefore", totalBefore,
                "countsAfter", counts);
    }

    // ✅ Undo last organization
    @PostMapping("/undo")
    public Map<String, Object> undoOrganize(@RequestParam String folderPath) throws IOException {
        List<Path> movedFiles = lastOperation.get(folderPath);
        if (movedFiles == null || movedFiles.isEmpty()) {
            return Map.of("error", "No operation to undo");
        }

        for (Path path : movedFiles) {
            if (Files.exists(path)) {
                Path parentDir = path.getParent().getParent(); // original folder
                Path targetPath = parentDir.resolve(path.getFileName());
                Files.move(path, targetPath, StandardCopyOption.REPLACE_EXISTING);
            }
        }

        lastOperation.remove(folderPath);

        return Map.of("message", "Undo successful! Files moved back.");
    }

    // ✅ Helper to get extension
    private String getFileExtension(String name) {
        int lastDot = name.lastIndexOf(".");
        return (lastDot == -1) ? "unknown" : name.substring(lastDot + 1).toLowerCase();
    }
}