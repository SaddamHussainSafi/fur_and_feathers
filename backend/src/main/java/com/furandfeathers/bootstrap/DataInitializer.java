package com.furandfeathers.bootstrap;

import com.furandfeathers.user.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Ensure roles
        for (RoleName rn : RoleName.values()) {
            roleRepository.findByName(rn).orElseGet(() -> roleRepository.save(Role.builder().name(rn).build()));
        }

        // Ensure default admin
        String adminEmail = "admin@furandfeathers.com";
        if (!userRepository.existsByEmail(adminEmail)) {
            User admin = User.builder()
                    .email(adminEmail)
                    .fullName("Admin")
                    .password(passwordEncoder.encode("admin123"))
                    .enabled(true)
                    .build();
            roleRepository.findByName(RoleName.ROLE_ADMIN).ifPresent(r -> admin.getRoles().add(r));
            userRepository.save(admin);
        }
    }
}
