package com.example.javatraining.commandLineRunner;

import com.example.javatraining.entities.User;
import com.example.javatraining.enums.RoleEnum;
import com.example.javatraining.repositories.UserRepository;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Getter
@Setter
public class CreateDefaultAdmin implements CommandLineRunner {
    UserRepository userRepository;
    Environment env;
    PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User admin = this.userRepository.findByEmail(this.env.getProperty("default-admin.email")).orElse(null);

        if (admin == null) {
            User newAdmin = new User();
            newAdmin.setEmail(this.env.getProperty("default-admin.email"));
            newAdmin.setPhone(this.env.getProperty("default-admin.phone"));
            newAdmin.setName(this.env.getProperty("default-admin.name"));
            newAdmin.setPassword(passwordEncoder.encode(this.env.getProperty("default-admin.password")));
            newAdmin.setRole(RoleEnum.ADMIN.name());

            userRepository.save(newAdmin);
        }
    }
}
