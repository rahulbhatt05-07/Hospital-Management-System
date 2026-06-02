package com.learning.HospitalManagementSystem.controller;

import com.learning.HospitalManagementSystem.entity.Insurance;
import com.learning.HospitalManagementSystem.service.InsuranceService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@RequestMapping("/api/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    @GetMapping
    public List<Insurance> getInsurance() {
       return insuranceService.getAllInsurance();
    }
    @PostMapping("/{patientId}")
    @PreAuthorize("hashRole('ADMIN')")
    public Insurance createInsurance(@PathVariable Long patientId, @RequestBody Insurance insurance) {
       return insuranceService.creteInsurance(insurance,patientId);
    }
}
