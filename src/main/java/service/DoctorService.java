package service;


import dto.DoctorDTO;
import entity.Doctor;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.EntityDtoMapper;
import org.springframework.stereotype.Service;
import repository.DoctorRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorDTO addDoctor(DoctorDTO dto) {
        Doctor saved = doctorRepository.save(EntityDtoMapper.toEntity(dto));
        return EntityDtoMapper.toDto(saved);
    }

    public List<DoctorDTO> getAllDoctors() {
        return doctorRepository.findAll().stream().map(EntityDtoMapper::toDto).collect(Collectors.toList());
    }

    public DoctorDTO getDoctorById(Long id) {
        Doctor d = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Doctor not found with ID: " + id));
        return EntityDtoMapper.toDto(d);
    }

    public void deleteDoctor(Long id) {
        if(!doctorRepository.existsById(id)) throw new ResourceNotFoundException("Doctor not found");
        doctorRepository.deleteById(id);
    }
}
