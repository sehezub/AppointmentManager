package com.example.AppointmentManager.service.Impl;

import com.example.AppointmentManager.domain.Doctor;
import com.example.AppointmentManager.domain.MedicalAppointment;
import com.example.AppointmentManager.domain.Patient;
import com.example.AppointmentManager.dto.DoctorDto;
import com.example.AppointmentManager.dto.MedicalAppointmentDto;
import com.example.AppointmentManager.dto.PatientDto;
import com.example.AppointmentManager.exceptions.*;
import com.example.AppointmentManager.mappers.DoctorMapper;
import com.example.AppointmentManager.mappers.MedicalAppointmentMapper;
import com.example.AppointmentManager.mappers.PatientMapper;
import com.example.AppointmentManager.repository.DoctorRepository;
import com.example.AppointmentManager.repository.MedicalAppointmentRepository;
import com.example.AppointmentManager.repository.PatientRepository;
import com.example.AppointmentManager.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentServiceImpl implements AppointmentService {
    private final MedicalAppointmentRepository medicalAppointmentRepo;
    private final PatientRepository patientRepo;
    private final DoctorRepository doctorRepo;
    private final PatientMapper patientMapper;
    private final DoctorMapper doctorMapper;
    private final MedicalAppointmentMapper medicalAppointmentMapper;
    public Patient getPatientById(Long id) {
        return patientRepo.findById(id).orElse(null);
    }

    public List<Patient> getPatients() {
        return patientRepo.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepo.findById(id).orElse(null);
    }

    public List<Doctor> getDoctors() {
        return doctorRepo.findAll();
    }

    public MedicalAppointment getAppointmentById(Long id) {
        return medicalAppointmentRepo.findById(id).orElse(null);
    }

    public List<MedicalAppointment> getMedicalAppointments() {
        return medicalAppointmentRepo.findAll();
    }

    public List<MedicalAppointment> getPatientAppointments(Long id) {
        if (getPatientById(id) == null) {
            throw new PatientDoesNotExistException("There is no patient with the given id");
        }
        return medicalAppointmentRepo.findByIdPatient(id);
    }
    public List<MedicalAppointment> getDoctorAppointments(Long id) {
        if (getDoctorById(id) == null) {
            throw new DoctorDoesNotExistException("There is no doctor with the given id");
        }
        return medicalAppointmentRepo.findByIdDoctor(id);
    }
    public List<MedicalAppointment> getAllAppointments() {
        return medicalAppointmentRepo.findAll();
    }
    public void noOverlapping(MedicalAppointment appointment) {
        List<MedicalAppointment> appointments = getPatientAppointments(appointment.getIdPatient());
        LocalDateTime start = appointment.getDate();
        LocalDateTime end = appointment.getDate().plusSeconds(appointment.getDuration());
        MedicalAppointment overlapping = appointments.stream()
                .filter(ap -> !ap.getDate().isAfter(end) && !start.isAfter(ap.getDate().plusSeconds(ap.getDuration())) )
                .findAny().orElse(null);
        if (overlapping != null) {
            throw new PatientAppointmentOverlapsException("the patient has another appointment that overlaps");
        }

        appointments = getDoctorAppointments(appointment.getIdDoctor());
        overlapping = appointments.stream()
                .filter(ap -> !ap.getDate().isAfter(end) && !start.isAfter(ap.getDate().plusSeconds(ap.getDuration())) )
                .findAny().orElse(null);
        if (overlapping != null) {
            throw new DoctorAppointmentOverlapsException("the doctor has another appointment that overlaps");
        }
    }
    public MedicalAppointment saveAppointment(MedicalAppointment appointment) {
        if (getPatientById(appointment.getIdPatient()) == null) {
            throw new PatientDoesNotExistException("There is no patient with the given id");
        }
        appointment.setIdPatient(appointment.getIdPatient());
        if (getDoctorById(appointment.getIdDoctor()) == null) {
            throw new DoctorDoesNotExistException("There is no doctor with the given id");
        }
        appointment.setIdDoctor(appointment.getIdDoctor());
        noOverlapping(appointment);
        medicalAppointmentRepo.save(appointment);
        return appointment;
    }

    public void deleteAppointment(Long id) {
        if (getAppointmentById(id) == null) {
            throw new AppointmentDoesNotExistException("No appointment with the given id");
        }
        medicalAppointmentRepo.deleteById(id);
    }

    public Patient savePatient(Patient patient) {
        patientRepo.save(patient);
        return patient;
    }

    public void deletePatient(Long id) {
        if (getPatientById(id) == null) {
            throw new PatientDoesNotExistException("No patient with the given id");
        }
        if (getPatientAppointments(id).size() > 0) {
            throw new PatientAppointmentOverlapsException("There is a an appointment with the given patient id");
        }
        patientRepo.deleteById(id);
    }

    public Doctor saveDoctor(Doctor doctor) {
        doctorRepo.save(doctor);
        return doctor;
    }

    public void deleteDoctor(Long id) {
        if (getDoctorById(id) == null) {
            throw new DoctorDoesNotExistException("No doctor with the given id");
        }
        if (getDoctorAppointments(id).size() > 0) {
            throw new DoctorAppointmentOverlapsException("There is a an appointment with the given doctor id");
        }
        doctorRepo.deleteById(id);
    }

    public Doctor doctorDtoToDoctor(DoctorDto doctorDto) {
        return doctorMapper.doctorDtoToDoctor(doctorDto);
    }

    public DoctorDto doctorToDoctorDto(Doctor doctor) {
        return doctorMapper.doctorToDoctorDto(doctor);
    }

    public MedicalAppointment MedicalAppointmentDtoToMedicalAppointment(MedicalAppointmentDto medicalAppointmentDto) {
        return medicalAppointmentMapper.MedicalAppointmentDtoToMedicalAppointment(medicalAppointmentDto);
    }

    public MedicalAppointmentDto MedicalAppointmentToMedicalAppointmentDto(MedicalAppointment medicalAppointment) {
        return medicalAppointmentMapper.MedicalAppointmentToMedicalAppointmentDto(medicalAppointment);
    }

    public Patient patientDtoToPatient(PatientDto patientDto) {
        return patientMapper.patientDtoToPatient(patientDto);
    }

    public PatientDto patientToPatientDto(Patient patient) {
        return patientMapper.patientToPatientDto(patient);
    }

}
