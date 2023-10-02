package com.example.AppointmentManager.repository;

import com.example.AppointmentManager.domain.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
}
