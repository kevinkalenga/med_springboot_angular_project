package com.example.med_spring_project.exception;

public class NotFoundException extends RuntimeException{
    public NotFoundException(String ex){
        super(ex);
    }
}
