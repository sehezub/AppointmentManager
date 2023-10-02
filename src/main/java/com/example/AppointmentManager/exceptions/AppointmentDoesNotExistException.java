package com.example.AppointmentManager.exceptions;

public class AppointmentDoesNotExistException extends RuntimeException{
  public AppointmentDoesNotExistException(String message) {super(message);}
}
