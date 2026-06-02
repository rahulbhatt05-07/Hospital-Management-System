package com.learning.HospitalManagementSystem.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class DoctorSignupDTO {
    // 🔐 Login fields (Jo User table mein jayengi)
    @NotBlank(message = "Username khali nahi hona chahiye")
    private String username;

    @NotBlank(message = "Password khali nahi hona chahiye")
    @Size(min = 6, message = "Password kam se kam 6 char ka hona chahiye")
    private String password;

    // 🩺 Doctor Profile fields (Jo Doctor table mein jayengi)
    @NotBlank(message = "Doctor ka naam zaroori hai")
    private String name;

    @NotBlank(message = "Email zaroori hai")
    @Email(message = "Email sahi format mein dalo")
    private String email;

    @NotBlank(message = "Specialization batana zaroori hai")
    private String specialization;

}
