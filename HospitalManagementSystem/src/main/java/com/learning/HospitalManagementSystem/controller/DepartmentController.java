package com.learning.HospitalManagementSystem.controller;

import com.learning.HospitalManagementSystem.entity.Department;
import com.learning.HospitalManagementSystem.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/departments")
@RequiredArgsConstructor
public class DepartmentController {

    private final DepartmentService departmentService;

    // Create department with HOD
    @PostMapping("/{hod}")
    @PreAuthorize("hasRole('ADMIN')")  // Only admin can create department
    public Department createDepartment(@PathVariable Long hod, @RequestBody Department department) {
        return departmentService.createDepartment(department, hod);
    }

    // Get all departments
    @GetMapping
    public List<Department> getAllDepartments() {
        return departmentService.getAllDepartments();
    }
}

