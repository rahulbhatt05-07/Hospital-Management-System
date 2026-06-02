package com.learning.HospitalManagementSystem.controller;

import com.learning.HospitalManagementSystem.entity.Insurance;
import com.learning.HospitalManagementSystem.entity.Patient;
import com.learning.HospitalManagementSystem.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/insurance")
@RequiredArgsConstructor
public class InsuranceController {

    private final InsuranceService insuranceService;

    // Assign insurance to patient
    @PostMapping("/assign/{patientId}")
    public Patient assignInsurance(@PathVariable Long patientId, @RequestBody Insurance insurance) {
        return insuranceService.assignInsuranceToPatient(patientId, insurance);
    }

    // Disassociate insurance from patient
    @PutMapping("/disassociate/{patientId}")
    public Patient disassociateInsurance(@PathVariable Long patientId) {
        return insuranceService.disassociateInsurance(patientId);
    }
}

