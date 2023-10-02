package com.example.AppointmentManager.mappers;

import com.example.AppointmentManager.domain.Doctor;
import com.example.AppointmentManager.dto.DoctorDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DoctorMapper {
    Doctor doctorDtoToDoctor(DoctorDto doctorDto);
    DoctorDto doctorToDoctorDto(Doctor doctor);
}
