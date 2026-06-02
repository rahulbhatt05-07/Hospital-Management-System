package com.learning.HospitalManagementSystem.controller;


import com.learning.HospitalManagementSystem.dto.AppointmentResponse;
import com.learning.HospitalManagementSystem.entity.Appointment;
import com.learning.HospitalManagementSystem.service.AppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final AppointmentService appointmentService;

    @GetMapping
    public List<AppointmentResponse> getAllAppointments() {

        return appointmentService.getAllAppointments();
    }

    @GetMapping("/{id}")
    public Appointment getAppointment(@PathVariable Long id) {
        return appointmentService.getAppointmentById(id);
    }

    @PostMapping("/{patientId}/{doctorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'PATIENT')")
    public AppointmentResponse createAppointment(@Valid  @PathVariable Long patientId,
                                                 @PathVariable Long doctorId,
                                                 @RequestBody Appointment appointment) {
        return appointmentService.createNewAppointment(appointment, patientId, doctorId);
    }

    @PutMapping("/{id}/doctor/{doctorId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'DOCTOR' , 'PATIENT')")
    public Appointment changeDoctor(@PathVariable Long id, @PathVariable Long doctorId) {
        appointmentService.assignNewDoctorToAppointment(id, doctorId);
        return appointmentService.getAppointmentById(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteAppointment(@PathVariable Long id) {
        appointmentService.deleteAppointment(id);
        return "Appointment deleted with id: " + id;
    }
}

