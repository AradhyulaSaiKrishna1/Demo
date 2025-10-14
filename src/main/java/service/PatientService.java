package service;

import dto.PatientDTO;
import entity.Patient;
import exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import mapper.EntityDtoMapper;
import org.springframework.stereotype.Service;
import repository.PatientRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientDTO addPatient(PatientDTO dto) {
        Patient entity = EntityDtoMapper.toEntity(dto);
        Patient saved = patientRepository.save(entity);
        return EntityDtoMapper.toDto(saved);
    }

    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll()
                .stream()
                .map(EntityDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public PatientDTO getPatientById(Long id) {
        Patient p = patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found"));
        return EntityDtoMapper.toDto(p);
    }

    public void deletePatient(Long id) {
        if(!patientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Patient not found");
        }
        patientRepository.deleteById(id);
    }
}
