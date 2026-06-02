package com.learning.HospitalManagementSystem.service;



import com.learning.HospitalManagementSystem.dto.DoctorSignupDTO;
import com.learning.HospitalManagementSystem.entity.Doctor;
import com.learning.HospitalManagementSystem.entity.Role;
import com.learning.HospitalManagementSystem.entity.User;
import com.learning.HospitalManagementSystem.repository.DoctorRepository;

import com.learning.HospitalManagementSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoctorService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final DoctorRepository doctorRepository;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public Doctor getDoctorById(Long id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));
    }

    @Transactional
    public Doctor createDoctor(DoctorSignupDTO doctor) {
        if (userRepository.findByUsername(doctor.getUsername()).isPresent()) {
            throw new RuntimeException("Username is already in use");
        }
        User user = User.builder()
                .username(doctor.getUsername())
                .password(passwordEncoder.encode(doctor.getPassword()))
                .role(Role.DOCTOR).build();

        User saved = userRepository.save(user);

        Doctor newDoc = Doctor.builder()
                .name(doctor.getName())
                .email(doctor.getEmail())
                .specialization(doctor.getSpecialization())
                .user(saved)
                .build();


        return doctorRepository.save(newDoc);
    }

    @Transactional
    public Doctor updateDoctor(Long id, Doctor doctorDetails) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        doctor.setName(doctorDetails.getName());
        doctor.setEmail(doctorDetails.getEmail());
        doctor.setSpecialization(doctorDetails.getSpecialization());
        return doctorRepository.save(doctor);
    }

    @Transactional
    public void deleteDoctor(Long id) {
        doctorRepository.deleteById(id);
    }
}
