package com.furandfeathers.controller;

import com.furandfeathers.model.User;
import com.furandfeathers.security.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class BaseController {
    
    protected User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetailsImpl) {
            UserDetailsImpl userDetails = (UserDetailsImpl) principal;
            User user = new User();
            user.setId(userDetails.getId());
            user.setUsername(userDetails.getUsername());
            user.setEmail(userDetails.getEmail());
            return user;
        }
        
        return null;
    }
    
    protected Long getCurrentUserId() {
        User user = getCurrentUser();
        return user != null ? user.getId() : null;
    }
}
