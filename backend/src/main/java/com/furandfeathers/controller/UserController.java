package com.furandfeathers.controller;

import com.furandfeathers.dto.request.UpdateUserRequest;
import com.furandfeathers.dto.response.MessageResponse;
import com.furandfeathers.dto.response.UserProfileResponse;
import com.furandfeathers.model.User;
import com.furandfeathers.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController extends BaseController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('SHELTER_OWNER') or hasRole('ADMIN')")
    public ResponseEntity<UserProfileResponse> getCurrentUserProfile() {
        User user = userService.getUserById(getCurrentUserId());
        return ResponseEntity.ok(new UserProfileResponse(user));
    }

    @PutMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('SHELTER_OWNER') or hasRole('ADMIN')")
    public ResponseEntity<UserProfileResponse> updateCurrentUser(
            @Valid @RequestBody UpdateUserRequest updateRequest) {
        User updatedUser = userService.updateUser(getCurrentUserId(), updateRequest);
        return ResponseEntity.ok(new UserProfileResponse(updatedUser));
    }

    @DeleteMapping("/me")
    @PreAuthorize("hasRole('USER') or hasRole('SHELTER_OWNER') or hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteCurrentUser() {
        userService.deleteUser(getCurrentUserId());
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfileResponse> getUserById(@PathVariable Long id) {
        User user = userService.getUserById(id);
        return ResponseEntity.ok(new UserProfileResponse(user));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<UserProfileResponse> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest updateRequest) {
        User updatedUser = userService.updateUser(id, updateRequest);
        return ResponseEntity.ok(new UserProfileResponse(updatedUser));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MessageResponse> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.ok(new MessageResponse("User deleted successfully!"));
    }
}
