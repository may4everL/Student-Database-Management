package com.example.studentmanagement.exceptions;

public class StudentNonExistenceException extends RuntimeException{
    public StudentNonExistenceException(String message) {
        super(message);
    }
}
