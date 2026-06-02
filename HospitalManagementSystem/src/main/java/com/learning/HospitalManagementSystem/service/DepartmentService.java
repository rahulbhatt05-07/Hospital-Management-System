package com.learning.HospitalManagementSystem.service;
import com.learning.HospitalManagementSystem.entity.Department;
import com.learning.HospitalManagementSystem.entity.Doctor;
import com.learning.HospitalManagementSystem.repository.DepartmentRepository;
import com.learning.HospitalManagementSystem.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    @Transactional
    public Department createDepartment(Department department, Long hod) {
        Doctor newhod = doctorRepository.findById(hod)
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        // Ensure doctors set is not null
        if (department.getDoctors() == null) {
            department.setDoctors(new HashSet<>());
        }

        Department savedDept = departmentRepository.save(department);

        savedDept.setHod(newhod);
        savedDept.getDoctors().add(newhod);
        newhod.getDepartments().add(savedDept);  // 🔥 YE MISSING HAI

        return savedDept;
    }


}


