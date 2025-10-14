package mapper;


import dto.*;
import entity.*;

import java.time.LocalDateTime;

public class EntityDtoMapper {

    // Patient
    public static PatientDTO toDto(Patient p) {
        if (p == null) return null;
        return new PatientDTO(p.getId(), p.getName(), p.getEmail(), p.getPhone(), p.getDateOfBirth());
    }

    public static Patient toEntity(PatientDTO dto) {
        if (dto == null) return null;
        Patient p = new Patient();
        p.setId(dto.getId());
        p.setName(dto.getName());
        p.setEmail(dto.getEmail());
        p.setPhone(dto.getPhone());
        p.setDateOfBirth(dto.getDateOfBirth());
        return p;
    }

    // Doctor
    public static DoctorDTO toDto(Doctor d) {
        if (d == null) return null;
        return new DoctorDTO(d.getId(), d.getName(), d.getSpecialization(), d.getContactNumber());
    }

    public static Doctor toEntity(DoctorDTO dto) {
        if (dto == null) return null;
        Doctor d = new Doctor();
        d.setId(dto.getId());
        d.setName(dto.getName());
        d.setSpecialization(dto.getSpecialization());
        d.setContactNumber(dto.getContactNumber());
        return d;
    }

    // Appointment
    public static AppointmentDTO toDto(Appointment a) {
        if (a == null) return null;
        Long patientId = a.getPatient() != null ? a.getPatient().getId() : null;
        Long doctorId = a.getDoctor() != null ? a.getDoctor().getId() : null;
        return new AppointmentDTO(a.getId(), patientId, doctorId, a.getAppointmentTime(), a.getStatus());
    }

    public static Appointment toEntity(AppointmentDTO dto, Patient patient, Doctor doctor) {
        if (dto == null) return null;
        Appointment a = new Appointment();
        a.setId(dto.getId());
        a.setPatient(patient);
        a.setDoctor(doctor);
        a.setAppointmentTime(dto.getAppointmentTime() != null ? dto.getAppointmentTime() : LocalDateTime.now().plusDays(1));
        a.setStatus(dto.getStatus() != null ? dto.getStatus() : "Scheduled");
        return a;
    }
}
