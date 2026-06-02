package com.learning.HospitalManagementSystem.repository;

import com.learning.HospitalManagementSystem.entity.Insurance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<Insurance, Long> {
    // Optional: findByPolicyNumber
}
