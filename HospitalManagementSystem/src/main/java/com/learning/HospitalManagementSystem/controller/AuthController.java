package com.learning.HospitalManagementSystem.controller;

import com.learning.HospitalManagementSystem.dto.LoginRequestDTO;
import com.learning.HospitalManagementSystem.dto.LoginResponseDTO;
import com.learning.HospitalManagementSystem.dto.SignupRequestDTO;
import com.learning.HospitalManagementSystem.dto.SignupResponseDTO;
import com.learning.HospitalManagementSystem.security.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
     public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        return ResponseEntity.ok(authService.login(loginRequestDTO));
    }

 @PostMapping("/signup")
    public SignupResponseDTO signup(@RequestBody SignupRequestDTO signupRequestDTO) {
        return authService.signup(signupRequestDTO);

 }
}
