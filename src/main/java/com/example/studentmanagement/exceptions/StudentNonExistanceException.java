package com.example.studentmanagement.exceptions;

public class StudentNonExistanceException extends RuntimeException{
    public StudentNonExistanceException(String message) {
        super(message);
    }
}
