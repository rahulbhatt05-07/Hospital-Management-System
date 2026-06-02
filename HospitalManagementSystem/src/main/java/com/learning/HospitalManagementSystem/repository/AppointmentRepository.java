package com.learning.HospitalManagementSystem.repository;

import com.learning.HospitalManagementSystem.dto.AppointmentResponse;
import com.learning.HospitalManagementSystem.entity.Appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    // Optional: findByPatientId, findByDoctorId etc.

    @Query("SELECT new com.learning.HospitalManagementSystem.dto.AppointmentResponse(" +
            "a.id, a.appointmentTime, a.reason, " +
            "p.id, p.name, " +
            "d.id, d.name) " +
            "FROM Appointment a " +
            "JOIN a.patient p " +    // Yahan space check kar 'p' ke baad
            "JOIN a.doctor d")      // Yahan space check kar 'd' se pehle
    List<AppointmentResponse> findAllAppointments();

}

