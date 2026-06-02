package com.learning.HospitalManagementSystem.repository;


import com.learning.HospitalManagementSystem.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    // Optional: findByEmail, findBySpecialization etc.
}

