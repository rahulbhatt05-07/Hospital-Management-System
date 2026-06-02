package com.learning.HospitalManagementSystem.service;

import com.learning.HospitalManagementSystem.entity.Insurance;
import com.learning.HospitalManagementSystem.entity.Patient;
import com.learning.HospitalManagementSystem.repository.InsuranceRepository;
import com.learning.HospitalManagementSystem.repository.PatientRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.InputMismatchException;
import java.util.List;
@Service
@RequiredArgsConstructor
public class InsuranceService {

    private final InsuranceRepository insuranceRepository;
    private final PatientRepository patientRepository;


    public List<Insurance> getAllInsurance() {
       return insuranceRepository.findAll();
    }

    @Transactional
    public Insurance creteInsurance(Insurance insurance, Long id) {
       Patient newPat= patientRepository.findById(id).orElseThrow(()-> new InputMismatchException("Patinet not found"));
       newPat.setInsurance(insurance);
       insurance.setPatient(newPat);
       return insuranceRepository.save(insurance);

    }
}
