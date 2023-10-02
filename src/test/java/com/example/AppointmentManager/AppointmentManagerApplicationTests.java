package com.example.AppointmentManager;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.FileCopyUtils;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileReader;

import org.springframework.test.jdbc.JdbcTestUtils;

@SpringBootTest
@Sql({"/schema.sql"})
@AutoConfigureMockMvc
public abstract class AppointmentManagerApplicationTests {


  @Autowired
  protected MockMvc mockMvc;
  @Autowired
  protected ObjectMapper objectMapper;
  @Autowired
  protected JdbcTemplate jdbcTemplate;
  @Autowired
  protected EntityManager entityManager;

  @SneakyThrows
  protected String getContentFromFile(String filePath) {
    File file = new ClassPathResource(filePath).getFile();
    return FileCopyUtils.copyToString(new FileReader(file));
  }
}
