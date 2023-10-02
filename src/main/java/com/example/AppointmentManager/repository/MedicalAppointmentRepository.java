package com.example.AppointmentManager.repository;

import com.example.AppointmentManager.domain.MedicalAppointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedicalAppointmentRepository extends JpaRepository<MedicalAppointment, Long> {
    List<MedicalAppointment> findByIdPatient(Long id);
    List<MedicalAppointment> findByIdDoctor(Long id);
}
