package com.example.AppointmentManager.controller;

import com.example.AppointmentManager.domain.Doctor;
import com.example.AppointmentManager.domain.MedicalAppointment;
import com.example.AppointmentManager.domain.Patient;
import com.example.AppointmentManager.dto.DoctorDto;
import com.example.AppointmentManager.dto.MedicalAppointmentDto;
import com.example.AppointmentManager.dto.PatientDto;
import com.example.AppointmentManager.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;

    public void AppointmentService(AppointmentService aps) {
        this.appointmentService = aps;
    }
    @GetMapping("/patients")
    public List<Patient> getPatients() {
        return appointmentService.getPatients();
    }
    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable("id") Long id) {
        return appointmentService.getPatientById(id);
    }
    @DeleteMapping("/patients/{id}")
    public void deletePatient(@PathVariable Long id) {
        appointmentService.deletePatient(id);
    }
    @GetMapping("/patients/{id}/appointments")
    public List<MedicalAppointment> getPatientAppointments(@PathVariable() Long id) {
        return appointmentService.getPatientAppointments(id);
    }
    @GetMapping("/doctors")
    public List<Doctor> getDoctors() {
        return appointmentService.getDoctors();
    }
    @GetMapping("/doctors/{id}")
    public Doctor getDoctor(@PathVariable Long id) {
        return appointmentService.getDoctorById(id);
    }
    @DeleteMapping("/doctors/{id}")
    public void deleteDoctor(@PathVariable Long id) {
        appointmentService.deleteDoctor(id);
    }
    @GetMapping("/doctors/{id}/appointments")
    public List<MedicalAppointment> getDoctorAppointments(@PathVariable Long id) {
        return appointmentService.getDoctorAppointments(id);
    }
    @GetMapping("/appointments")
    public List<MedicalAppointment> getMedicalAppointments() {
        return appointmentService.getMedicalAppointments();
    }
    @GetMapping("/appointments/{id}")
    public MedicalAppointment getAppointment(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }
    @DeleteMapping("/appointments/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
    }
    @PostMapping("/patients")
    public Patient savePatient(@RequestBody PatientDto patientDto) {
        return appointmentService.savePatient(appointmentService.patientDtoToPatient(patientDto));
    }
    @PostMapping("/doctors")
    public Doctor saveDoctor(@RequestBody DoctorDto doctorDto) {

        return appointmentService.saveDoctor(appointmentService.doctorDtoToDoctor(doctorDto));
    }
    @PostMapping("/appointments")
    public MedicalAppointment saveAppointment(@RequestBody MedicalAppointmentDto appointmentDto) {
        return appointmentService.saveAppointment(appointmentService.MedicalAppointmentDtoToMedicalAppointment(appointmentDto));
    }
    @PostMapping("/patients/{id}/appointments")
    public MedicalAppointment saveAppointmentPatient(@PathVariable Long id, @RequestBody MedicalAppointmentDto appointmentDto) {
        appointmentDto.setIdPatient(id);
        return appointmentService.saveAppointment(appointmentService.MedicalAppointmentDtoToMedicalAppointment(appointmentDto));
    }
    @PostMapping("/doctors/{id}/appointments")
    public MedicalAppointment saveAppointmentDoctor(@PathVariable Long id, @RequestBody MedicalAppointmentDto appointmentDto) {
        appointmentDto.setIdDoctor(id);
        return appointmentService.saveAppointment(appointmentService.MedicalAppointmentDtoToMedicalAppointment(appointmentDto));
    }
}
