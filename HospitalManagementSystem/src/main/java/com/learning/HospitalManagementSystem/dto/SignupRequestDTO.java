package com.learning.HospitalManagementSystem.dto;



import com.learning.HospitalManagementSystem.BloodGroupType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignupRequestDTO {

    // 🔐 Security/Login Fields
    @NotBlank(message = "Username should not be null or empty")
    @Length(min = 3, max = 20)
    private String username;

    @NotBlank
    @Size(min = 4, message = "min 4 char")
    private String password;

    // 🧑 Patient Profile Fields
    @NotBlank(message = "Required")
    @Size(max = 40)
    private String name;

    @NotBlank(message = "Email required")
    @Email(message = "email should be in right format")
    private String email;

    @NotNull(message = "Birthdate required")
    @Past(message = "Birthdate must be in the past")
    private LocalDate birthdate;

    private String gender;

    @Enumerated(EnumType.STRING)
    private BloodGroupType bloodGroup;
}