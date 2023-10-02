package com.example.AppointmentManager.domain;


import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Medical_Appointment")
@Data
public class MedicalAppointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="appointment_date")
    private LocalDateTime date;
    private String details;
    @Column(name="patient_id")
    private Long idPatient;
    @Column(name="doctor_id")
    private Long idDoctor;
    private Long duration;
}
