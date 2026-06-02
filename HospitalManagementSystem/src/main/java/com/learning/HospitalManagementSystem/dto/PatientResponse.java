package com.learning.HospitalManagementSystem.dto;

import com.learning.HospitalManagementSystem.BloodGroupType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PatientResponse {

    private Long id;
    private String name ;
    private BloodGroupType bloodGroup;
    private String reason;
     private String policyNumber;

}
