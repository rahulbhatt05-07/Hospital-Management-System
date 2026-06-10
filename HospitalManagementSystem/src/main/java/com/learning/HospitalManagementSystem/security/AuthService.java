package com.learning.HospitalManagementSystem.security;

import com.learning.HospitalManagementSystem.dto.LoginRequestDTO;
import com.learning.HospitalManagementSystem.dto.LoginResponseDTO;
import com.learning.HospitalManagementSystem.dto.SignupRequestDTO;
import com.learning.HospitalManagementSystem.dto.SignupResponseDTO;
import com.learning.HospitalManagementSystem.entity.Doctor;
import com.learning.HospitalManagementSystem.entity.Patient;
import com.learning.HospitalManagementSystem.entity.Role;
import com.learning.HospitalManagementSystem.entity.User;
import com.learning.HospitalManagementSystem.repository.DoctorRepository;
import com.learning.HospitalManagementSystem.repository.PatientRepository;
import com.learning.HospitalManagementSystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final DoctorRepository doctorRepository;

    private final UserRepository userRepository;
    private final AuthUtil authUtil;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final PatientRepository patientRepository;

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {

        System.out.println("step 1");
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDTO.getUsername(), loginRequestDTO.getPassword()));
        System.out.println("step 2");
        User user = (User) authentication.getPrincipal();
        System.out.println("step 3");
        String token = authUtil.generateAccessToken(user);
        System.out.println("step 4");
        return new LoginResponseDTO(token, user.getId());
    }

    public SignupResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        User user = userRepository.findByUsername(signupRequestDTO.getUsername()).orElse(null);

        if (user != null) throw new RuntimeException("Username is already in use");

        user = userRepository.save(User.builder()
                .username(signupRequestDTO.getUsername())
                .password(passwordEncoder.encode(signupRequestDTO.getPassword()))
                .role(Role.PATIENT).build());


        Patient patient = new Patient();
        patient.setName(signupRequestDTO.getName());
        patient.setEmail(signupRequestDTO.getEmail());
        patient.setBirthdate(signupRequestDTO.getBirthdate());
        patient.setGender(signupRequestDTO.getGender());
        patient.setBloodGroup(signupRequestDTO.getBloodGroup());

        // 🚨 Sabse main kaam: Saved User ko Patient ke sath bind/link kar diya
        patient.setUser(user);
        patientRepository.save(patient);
        return new SignupResponseDTO(user.getId(), user.getUsername());
    }

}