package com.example.lunch.exception;

public class InvalidFileContentException extends RuntimeException {

    public InvalidFileContentException(String filename) {
        super (String.format("Invalid file content %s.",filename));
    }
}
