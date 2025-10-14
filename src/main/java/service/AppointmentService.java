package service;

import dto.*;
import entity.*;
import exception.ResourceNotFoundException;
import mapper.EntityDtoMapper;
import repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public AppointmentDTO bookAppointment(AppointmentRequestDTO req) {
        Patient patient = patientRepository.findById(req.getPatientId())
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with ID: " + req.getPatientId()));

        Doctor doctor = doctorRepository.findById(req.getDoctorId())
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + req.getDoctorId()));

        Appointment appointment = new Appointment();
        appointment.setPatient(patient);
        appointment.setDoctor(doctor);
        appointment.setAppointmentTime(LocalDateTime.now().plusDays(1));
        appointment.setStatus("Scheduled");

        Appointment saved = appointmentRepository.save(appointment);
        return EntityDtoMapper.toDto(saved);
    }

    public List<AppointmentDTO> getAllAppointments() {
        return appointmentRepository.findAll().stream().map(EntityDtoMapper::toDto).collect(Collectors.toList());
    }

    public AppointmentDTO getAppointmentById(Long id) {
        Appointment a = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        return EntityDtoMapper.toDto(a);
    }

    public AppointmentDTO updateStatus(Long id, String status) {
        Appointment a = appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with ID: " + id));
        a.setStatus(status);
        return EntityDtoMapper.toDto(appointmentRepository.save(a));
    }
}