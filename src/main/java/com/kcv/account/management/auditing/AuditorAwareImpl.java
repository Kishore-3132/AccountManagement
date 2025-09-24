package com.kcv.account.management.auditing;

import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component("auditorProvider")
public class AuditorAwareImpl implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        // If you are using Spring Security:
         return Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication().getName());

        // For now, fallback to "SYSTEM"
//        return Optional.of("SYSTEM");
    }
}
