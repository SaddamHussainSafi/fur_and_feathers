package com.furandfeathers.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/health")
public class HealthController {
    @GetMapping
    public ResponseEntity<?> health() {
        return ResponseEntity.ok(new Object() {
            public final String status = "ok";
        });
    }
}
