package com.example.AppointmentManager.controllers;

import com.example.AppointmentManager.AppointmentManagerApplicationTests;
import com.example.AppointmentManager.domain.Doctor;
import com.example.AppointmentManager.domain.MedicalAppointment;
import com.example.AppointmentManager.domain.Patient;
import com.example.AppointmentManager.dto.PatientDto;
import com.example.AppointmentManager.service.AppointmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class AppointmentManagerControllerTest  extends AppointmentManagerApplicationTests {


    @MockBean
    private AppointmentService appointmentService;


    @Test
    @SneakyThrows
    public void getPatients_returnListOfPatients() {
        List<Patient> patients = new ArrayList<>();
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("A");
        patients.add(patient);
        patient = new Patient();
        patient.setId(2L);
        patient.setName("B");
        patients.add(patient);
        patient = new Patient();
        patient.setId(3L);
        patient.setName("C");
        patients.add(patient);
        when(appointmentService.getPatients()).thenReturn(patients);
        mockMvc.perform(get("/patients"))
                .andExpect(status().isOk())
                .andExpect(content().json("{{'id': 1, 'name':'A'}, {'id': 2, 'name':'B'}, {'id': 3, 'name':'C'}}"));
    }
    @Test
    public void getDoctors_returnListOfDoctors() throws Exception {
        mockMvc.perform(get("/doctors"))
                .andExpect(status().isOk());
    }
    @Test
    @SneakyThrows
    public void getMedicalAppointments_returnListOfAppointments() {
        mockMvc.perform(get("/appointments"))
                .andExpect(status().isOk());
    }
    @Test
    @SneakyThrows
    public void getPatient_returnPatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("A");
        when(appointmentService.getPatientById(1L)).thenReturn(patient);
        mockMvc.perform(get("/patients/1"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id': 1, 'name':'A'}"));
    }
    @Test
    @SneakyThrows
    public void getDoctor_returnDoctor() {
        Doctor doctor = new Doctor();
        doctor.setId(2L);
        doctor.setName("B");
        when(appointmentService.getDoctorById(2L)).thenReturn(doctor);
        mockMvc.perform(get("/doctors/2"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'id':2,'name':'B'}"));
    }
    @Test
    @SneakyThrows
    public void getAppointment_returnMedicalAppointment() {
        MedicalAppointment appointment = new MedicalAppointment();
        appointment.setId(3L);
        appointment.setIdPatient(1L);
        appointment.setIdDoctor(2L);
        appointment.setDetails("x");
        appointment.setDuration(3600L);
        appointment.setDate(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        when(appointmentService.getAppointmentById(3L)).thenReturn(appointment);
        mockMvc.perform(get("/appointments/3"))
                .andExpect(status().isOk())
                .andExpect(content().json("{'date':'2023-01-01T00:00:00','details':'x'," +
                        "'idDoctor':2,'idPatient':1,'duration':3600}"));
    }
    @Test
    @SneakyThrows
    public void savePatient_returnPatient() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setName("A");
        PatientDto patientDto = new PatientDto();
        patientDto.setName("A");
        when(appointmentService.savePatient(patient)).thenReturn(patient);
        mockMvc.perform(post("/patients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(patientDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(patient)));
    }
}
