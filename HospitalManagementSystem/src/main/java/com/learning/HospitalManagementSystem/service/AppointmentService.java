package com.learning.HospitalManagementSystem.service;

import com.learning.HospitalManagementSystem.dto.AppointmentResponse;
import com.learning.HospitalManagementSystem.entity.Appointment;
import com.learning.HospitalManagementSystem.entity.Doctor;
import com.learning.HospitalManagementSystem.entity.Patient;
import com.learning.HospitalManagementSystem.repository.AppointmentRepository;
import com.learning.HospitalManagementSystem.repository.DoctorRepository;
import com.learning.HospitalManagementSystem.repository.PatientRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public List<AppointmentResponse> getAllAppointments() {
        return appointmentRepository.findAllAppointments();
    }

    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Appointment not found"));
    }

    @Transactional
    public AppointmentResponse createNewAppointment(Appointment appointment, Long patientId, Long doctorId) {
        Patient patient = patientRepository.findById(patientId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        if (appointment.getId() != null)
            throw new IllegalArgumentException("Appointment ID must be null for new appointment");

        appointment.setPatient(patient);
        appointment.setDoctor(doctor);

        patient.getAppointment().add(appointment);

         Appointment newAp=appointmentRepository.save(appointment);

        AppointmentResponse ar= modelMapper.map(newAp, AppointmentResponse.class);
        return ar;
    }

    @Transactional
    public void assignNewDoctorToAppointment(Long appointmentId, Long doctorId) {
        Appointment appointment = appointmentRepository.findById(appointmentId).orElseThrow();
        Doctor doctor = doctorRepository.findById(doctorId).orElseThrow();

        appointment.setDoctor(doctor);
    }

    @Transactional
    public void deleteAppointment(Long id) {
        appointmentRepository.deleteById(id);
    }
}

