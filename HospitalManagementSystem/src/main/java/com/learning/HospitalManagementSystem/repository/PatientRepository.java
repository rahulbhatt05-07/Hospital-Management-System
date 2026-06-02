package com.learning.HospitalManagementSystem.repository;


import com.learning.HospitalManagementSystem.dto.PatientResponse;
import com.learning.HospitalManagementSystem.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    @Query("SELECT new com.learning.HospitalManagementSystem.dto.PatientResponse(p.id, p.name, p.bloodGroup, a.reason, i.policyNumber) FROM Patient p LEFT JOIN p.appointment a LEFT JOIN p.insurance i")
    List<PatientResponse> findAllPatients();

}

