package com.furandfeathers.api;

import com.furandfeathers.api.dto.AuthResponse;
import com.furandfeathers.api.dto.LoginRequest;
import com.furandfeathers.api.dto.SignupRequest;
import com.furandfeathers.service.AuthService;
import com.furandfeathers.user.User;
import com.furandfeathers.user.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;

    @PostMapping("/signup")
    public ResponseEntity<AuthResponse> signup(@Valid @RequestBody SignupRequest request) {
        return ResponseEntity.ok(authService.signup(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @GetMapping("/me")
    public ResponseEntity<?> me(@AuthenticationPrincipal UserDetails principal) {
        if (principal == null) {
            return ResponseEntity.status(401).body("{\"error\":\"Unauthorized\"}");
        }
        User user = userRepository.findByEmail(principal.getUsername()).orElse(null);
        return ResponseEntity.ok(user == null ? "{}" : new Object() {
            public final Long id = user.getId();
            public final String email = user.getEmail();
            public final String fullName = user.getFullName();
            public final Object roles = user.getRoles().stream().map(r -> r.getName().name()).toList();
        });
    }
}
