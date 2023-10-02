package com.example.AppointmentManager.service;

import com.example.AppointmentManager.domain.Doctor;
import com.example.AppointmentManager.domain.MedicalAppointment;
import com.example.AppointmentManager.domain.Patient;
import com.example.AppointmentManager.dto.DoctorDto;
import com.example.AppointmentManager.dto.MedicalAppointmentDto;
import com.example.AppointmentManager.dto.PatientDto;

import java.util.List;

public interface AppointmentService {
    Patient getPatientById(Long id);
    List<Patient> getPatients();
    Doctor getDoctorById(Long id);
    List<Doctor> getDoctors();
    MedicalAppointment getAppointmentById(Long id);
    List<MedicalAppointment> getMedicalAppointments();

    List<MedicalAppointment> getPatientAppointments(Long id);
    List<MedicalAppointment> getDoctorAppointments(Long id);

    List<MedicalAppointment> getAllAppointments();

    void noOverlapping(MedicalAppointment appointment);
    MedicalAppointment saveAppointment(MedicalAppointment appointment);
    void deleteAppointment(Long id);
    Patient savePatient(Patient patient);
    void deletePatient(Long id);
    Doctor saveDoctor(Doctor doctor);
    void deleteDoctor(Long id);
    Doctor doctorDtoToDoctor(DoctorDto doctorDto);
    DoctorDto doctorToDoctorDto(Doctor doctor);
    MedicalAppointment MedicalAppointmentDtoToMedicalAppointment(MedicalAppointmentDto medicalAppointmentDto);
    MedicalAppointmentDto MedicalAppointmentToMedicalAppointmentDto(MedicalAppointment medicalAppointment);
    Patient patientDtoToPatient(PatientDto patientDto);
    PatientDto patientToPatientDto(Patient patient);
}
