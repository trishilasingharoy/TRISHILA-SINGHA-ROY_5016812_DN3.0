package com.example.employeemanagementsystem10.config;

import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // Here, you would return the username or identifier of the current user.
        // For simplicity, let's assume the username is "admin".
        return Optional.of("admin");
    }
}
