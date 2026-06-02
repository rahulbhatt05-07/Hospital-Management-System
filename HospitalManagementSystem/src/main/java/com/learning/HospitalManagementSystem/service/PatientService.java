package com.learning.HospitalManagementSystem.service;


import com.learning.HospitalManagementSystem.dto.PatientResponse;
import com.learning.HospitalManagementSystem.dto.SignupRequestDTO;
import com.learning.HospitalManagementSystem.entity.Patient;
import com.learning.HospitalManagementSystem.entity.Role;
import com.learning.HospitalManagementSystem.entity.User;
import com.learning.HospitalManagementSystem.repository.PatientRepository;

import com.learning.HospitalManagementSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PatientService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    public List<PatientResponse> getAllPatients() {
        return patientRepository.findAllPatients();
    }

    public Patient getPatientById(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Transactional
    public Patient createPatient(SignupRequestDTO signupRequestDTO) {
// 1. [Security Part] Check karo username unique hai ya nahi
        if (userRepository.findByUsername(signupRequestDTO.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use");
        }

        // 2. User table mein entry dalo (Bilkul tere purane signup jaisa)
        User user = User.builder()
                .username(signupRequestDTO.getUsername())
                .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
                .role(Role.PATIENT) // Admin bana raha hai tab bhi role PATIENT hi rahega
                .build();

        User savedUser = userRepository.save(user);

        // 3. [Patient Part] Ab bache huye fields se Patient ka object banao
        Patient patient = new Patient();
        patient.setName(signupRequestDTO.getName());
        patient.setEmail(signupRequestDTO.getEmail());
        patient.setBirthdate(signupRequestDTO.getBirthdate());
        patient.setGender(signupRequestDTO.getGender());
        patient.setBloodGroup(signupRequestDTO.getBloodGroup());

        // 🚨 Sabse main kaam: Saved User ko Patient ke sath bind/link kar diya
        patient.setUser(savedUser);

        // 4. Finally Patient ko save karo aur return kar do
        return patientRepository.save(patient);
    }

    @Transactional
    public Patient updatePatient(Long id, Patient patientDetails) {
        Patient patient = patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        patient.setName(patientDetails.getName());
        patient.setBirthdate(patientDetails.getBirthdate());
        patient.setEmail(patientDetails.getEmail());
        patient.setGender(patientDetails.getGender());
        patient.setBloodGroup(patientDetails.getBloodGroup());
        return patientRepository.save(patient);
    }

    @Transactional
    public void deletePatient(Long id) {
        patientRepository.deleteById(id);
    }
}
