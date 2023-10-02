package com.example.AppointmentManager.mappers;

import com.example.AppointmentManager.domain.MedicalAppointment;
import com.example.AppointmentManager.dto.MedicalAppointmentDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MedicalAppointmentMapper {
    MedicalAppointment MedicalAppointmentDtoToMedicalAppointment(MedicalAppointmentDto medicalAppointmentDto);
    MedicalAppointmentDto MedicalAppointmentToMedicalAppointmentDto(MedicalAppointment medicalAppointment);
}
