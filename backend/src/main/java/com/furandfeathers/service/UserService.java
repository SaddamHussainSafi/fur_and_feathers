package com.furandfeathers.service;

import com.furandfeathers.dto.request.UpdateUserRequest;
import com.furandfeathers.model.User;

import java.util.List;

public interface UserService {
    User getUserById(Long id);
    List<User> getAllUsers();
    User updateUser(Long id, UpdateUserRequest updateRequest);
    void deleteUser(Long id);
    boolean existsByUsername(String username);
    boolean existsByEmail(String email);
    User saveUser(User user);
}
