package com.learning.HospitalManagementSystem.security;



import com.learning.HospitalManagementSystem.entity.Role;
import com.learning.HospitalManagementSystem.entity.User;
import com.learning.HospitalManagementSystem.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AdminSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Check karenge ki kya database mein pehle se koi admin hai?
        if (userRepository.findByUsername("superadmin").isEmpty()) {

            User admin = User.builder()
                    .username("superadmin") // Admin ka username
                    .password(passwordEncoder.encode("Admin@123")) // Secure password
                    .role(Role.ADMIN)
                    .build();

            userRepository.save(admin);
            System.out.println("Default Admin account created successfully! Username: superadmin");
        }
    }
}