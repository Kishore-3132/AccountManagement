package com.kcv.account.management.security;

import com.kcv.account.management.dto.users.UserDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtils {

    private static final Logger logger = LoggerFactory.getLogger(SecurityUtils.class);

    public static MyUser getCurrentUser() {
        // Implementation to fetch the current user details from the security context
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        MyUser user = null;
        if(principal != null&& !principal.toString().equalsIgnoreCase("anonymousUser")) {
            user = (MyUser) principal;
        }
        return user;
    }
}
