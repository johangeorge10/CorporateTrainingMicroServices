package com.capstone.user.config;

import com.capstone.user.entity.Role;
import com.capstone.user.entity.User;
import com.capstone.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class AdminDataInitializer {

    @Bean
    CommandLineRunner createAdminIfNotExists(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder
    ) {
        return args -> {

            String adminEmail = "admin@capstone.com";

            boolean adminExists = userRepository.findByEmail(adminEmail).isPresent();

            if (!adminExists) {
                User admin = new User();
                admin.setName("System Admin");
                admin.setEmail(adminEmail);
                admin.setPassword(passwordEncoder.encode("admin123"));
                admin.setRole(Role.ADMIN);
                admin.setActive(true);

                userRepository.save(admin);

                System.out.println("ADMIN user created");
            } else {
                System.out.println("ADMIN user already exists");
            }
        };
    }
}
