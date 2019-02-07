package com.example.lunch.exception;

public class InvalidFileException extends RuntimeException {
    public InvalidFileException(String filename){
        super (String.format("Invalid file %s.",filename));
    }
}
