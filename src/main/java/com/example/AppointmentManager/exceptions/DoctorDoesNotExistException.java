package com.example.AppointmentManager.exceptions;

public class DoctorDoesNotExistException extends RuntimeException{
    public DoctorDoesNotExistException(String message) {super(message);}
}
