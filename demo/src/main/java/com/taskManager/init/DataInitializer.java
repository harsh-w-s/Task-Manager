package com.taskManager.init;

import com.taskManager.model.User;
import com.taskManager.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Set;

@Component
@Profile("!test") // don't run during tests unless desired
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepo;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserRepository userRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        if (!userRepo.existsByUsername("admin")) {
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin123")); // change the password
            admin.setCreatedAt(Instant.now());
            admin.setRoles(Set.of("ROLE_ADMIN", "ROLE_USER"));
            userRepo.save(admin);
            System.out.println("Created default admin / password: admin123");
        }
    }
}
