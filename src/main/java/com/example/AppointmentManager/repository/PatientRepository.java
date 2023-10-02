package com.example.AppointmentManager.repository;

import com.example.AppointmentManager.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {
}
