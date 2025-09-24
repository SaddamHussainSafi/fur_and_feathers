package com.furandfeathers.controller;

import com.furandfeathers.dto.request.LoginRequest;
import com.furandfeathers.dto.request.SignupRequest;
import com.furandfeathers.dto.request.TokenRefreshRequest;
import com.furandfeathers.dto.response.JwtResponse;
import com.furandfeathers.dto.response.MessageResponse;
import com.furandfeathers.dto.response.TokenRefreshResponse;
import com.furandfeathers.exception.TokenRefreshException;
import com.furandfeathers.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController extends BaseController {
    
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse response = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        authService.registerUser(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/refreshtoken")
    public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
        try {
            TokenRefreshResponse response = authService.refreshToken(request);
            return ResponseEntity.ok(response);
        } catch (TokenRefreshException e) {
            return ResponseEntity
                .badRequest()
                .body(new MessageResponse("Error: " + e.getMessage()));
        }
    }
    
    @PostMapping("/signout")
    public ResponseEntity<?> logoutUser() {
        Long userId = getCurrentUserId();
        if (userId != null) {
            authService.logoutUser(userId);
        }
        return ResponseEntity.ok(new MessageResponse("Log out successful!"));
    }
}
