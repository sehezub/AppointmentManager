package com.example.AppointmentManager.controllers;

import com.example.AppointmentManager.AppointmentManagerApplicationTests;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.jdbc.JdbcTestUtils;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Transactional
public class AppointmentManagerIntegTests extends AppointmentManagerApplicationTests {


  @Test
  @SneakyThrows
  public void getPatient_validIds_returnPatientJson() {
    mockMvc.perform(get("/patients/1"))
        .andExpect(status().isOk())
        .andExpect(content().json("{'id':1,'name':'Javier'}"));

    mockMvc.perform(get("/patients/2"))
        .andExpect(status().isOk())
        .andExpect(content().json("{'id':2,'name':'Daniela'}"));

    mockMvc.perform(get("/patients/3"))
        .andExpect(status().isOk())
        .andExpect(content().json("{'id':3,'name':'Tomas'}"));
  }
  @Test
  @SneakyThrows
  // TODO when error messages implemented
  public void getPatient_nonValidIds_returnError() {
    mockMvc.perform(get("/patients/4"))
        .andExpect(status().isNotFound());

    mockMvc.perform(get("/patients/5"))
        .andExpect(status().isNotFound());
  }
  @Test
  @SneakyThrows
  public void savePatient_validPatient_returnPatientJson() {
    int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Patient");

    mockMvc.perform(post("/patients")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getContentFromFile("/request/patient1Dto.json")))
        .andExpect(status().isOk())
        .andExpect(content().json(getContentFromFile("/request/patient1.json")));

    assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Patient")).isEqualTo(count+1);
  }
  @Test
  @SneakyThrows
  public void deletePatient_validId_returnVoid() {
    int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Patient");

    mockMvc.perform(delete("/patients/3"))
        .andExpect(status().isNoContent());
    entityManager.flush();

    assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Patient")).isEqualTo(count-1);
  }
  @Test
  @SneakyThrows
  public void getDoctor_validIds_returnDoctorJson() {
    mockMvc.perform(get("/doctors/1"))
        .andExpect(status().isOk())
        .andExpect(content().json("{'id':1,'name':'Jhon'}"));

    mockMvc.perform(get("/doctors/2"))
        .andExpect(status().isOk())
        .andExpect(content().json("{'id':2,'name':'Joseph'}"));

    mockMvc.perform(get("/doctors/3"))
        .andExpect(status().isOk())
        .andExpect(content().json("{'id':3,'name':'Jotaro'}"));
  }
  @Test
  @SneakyThrows
  // TODO
  public void getDoctor_nonValidIds_returnError() {
  }
  @Test
  @SneakyThrows
  public void saveDoctor_validDoctor_returnPatientJson() {
    int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Doctor");

    mockMvc.perform(post("/doctors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(getContentFromFile("/request/doctor1Dto.json")))
        .andExpect(status().isOk())
        .andExpect(content().json(getContentFromFile("/request/doctor1.json")));

    assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Doctor")).isEqualTo(count+1);
  }
  @Test
  @SneakyThrows
  public void deleteDoctor_validId_returnVoid() {
    int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Doctor");

    mockMvc.perform(delete("/doctors/3"))
        .andExpect(status().isNoContent());
    // TODO why not isOkay() even tho is correct
    entityManager.flush();

    assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Doctor")).isEqualTo(count-1);
  }
  @Test
  @SneakyThrows
  public void getAppointment_validIds_returnAppointmentJson() {
    mockMvc.perform(get("/appointments/1"))
        .andExpect(status().isOk())
        .andExpect(content().json(getContentFromFile("/request/appointment1.json")));

    mockMvc.perform(get("/appointments/2"))
        .andExpect(status().isOk())
        .andExpect(content().json(getContentFromFile("/request/appointment2.json")));
  }
  @Test
  @SneakyThrows
  public void deleteAppointment_validId_returnVoid() {
    int count = JdbcTestUtils.countRowsInTable(jdbcTemplate, "Medical_Appointment");

    mockMvc.perform(delete("/appointments/1"))
        .andExpect(status().isNoContent());
    entityManager.flush();

    assertThat(JdbcTestUtils.countRowsInTable(jdbcTemplate, "Medical_Appointment")).isEqualTo(count-1);
  }
}