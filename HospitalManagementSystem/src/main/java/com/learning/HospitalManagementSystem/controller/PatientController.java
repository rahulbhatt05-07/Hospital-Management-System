package com.learning.HospitalManagementSystem.controller;

import com.learning.HospitalManagementSystem.dto.PatientResponse;
import com.learning.HospitalManagementSystem.dto.SignupRequestDTO;
import com.learning.HospitalManagementSystem.entity.Patient;
import com.learning.HospitalManagementSystem.service.PatientService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // GET all patients
    @GetMapping
    public List<PatientResponse> getAllPatients() {
        return patientService.getAllPatients();
    }

    // GET patient by id
    @GetMapping("/{id}")
    public Patient getPatient(@PathVariable Long id) {
        return patientService.getPatientById(id);
    }

    // POST create new patient
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')") // Only admin can create new patients
    public Patient createPatient(@Valid @RequestBody SignupRequestDTO signupRequestDTO) {
        return patientService.createPatient(signupRequestDTO);


    }

    // PUT update patient
    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','PATIENT')")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    // DELETE patient
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deletePatient(@PathVariable Long id) {
        patientService.deletePatient(id);
        return "Patient deleted with id: " + id;
    }
}
