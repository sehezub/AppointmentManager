package com.example.AppointmentManager.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MedicalAppointmentDto {
    private LocalDateTime date;
    private String details;
    private Long idPatient;
    private Long idDoctor;
    private Long duration;
}
