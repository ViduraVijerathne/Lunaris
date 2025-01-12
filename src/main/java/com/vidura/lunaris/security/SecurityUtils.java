package com.vidura.lunaris.security;

import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {
    public static UserPrincipal getCurrentUser() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserPrincipal) {
            return (UserPrincipal) authentication.getPrincipal();
        }
        throw new IllegalStateException("No logged-in user found");
    }
}
