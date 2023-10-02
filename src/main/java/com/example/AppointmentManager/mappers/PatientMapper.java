package com.example.AppointmentManager.mappers;

import com.example.AppointmentManager.domain.Patient;
import com.example.AppointmentManager.dto.PatientDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PatientMapper {
    @Mapping(target = "name", source = "name")
    Patient patientDtoToPatient(PatientDto patientDto);
    PatientDto patientToPatientDto(Patient patient);
}
