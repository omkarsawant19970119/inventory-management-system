package com.omkar.inventory.user.service;

import com.omkar.inventory.user.dto.UserRequest;
import com.omkar.inventory.user.dto.UserResponse;

import java.util.List;

public interface UserService {

    UserResponse createUser(UserRequest request);

    UserResponse getUserById(Long id);

    List<UserResponse> getAllUsers();

    UserResponse updateUser(Long id, UserRequest request);

    void deleteUser(Long id);

    List<UserResponse> getUsersByDepartment(String department);

    List<UserResponse> getUsersByRole(String role);

    List<UserResponse> getUsersByStatus(Boolean active);

    UserResponse getUserByEmail(String email);
}