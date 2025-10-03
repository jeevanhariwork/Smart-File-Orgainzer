package com.example.organizer.model;

import java.util.List;

public class FileOrganizeResponse {
    private String message;
    private List<String> movedFiles;

    public FileOrganizeResponse(String message, List<String> movedFiles) {
        this.message = message;
        this.movedFiles = movedFiles;
    }

    public String getMessage() {
        return message;
    }

    public List<String> getMovedFiles() {
        return movedFiles;
    }
}