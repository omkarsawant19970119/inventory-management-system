package com.omkar.inventory.user.controller;

import com.omkar.inventory.user.dto.UserRequest;
import com.omkar.inventory.user.dto.UserResponse;
import com.omkar.inventory.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody UserRequest request) {

        return new ResponseEntity<>(
                userService.createUser(request),
                HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUser(@PathVariable("id") Long id) {

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers() {

        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable("id") Long id,
            @Valid @RequestBody UserRequest request) {

        return ResponseEntity.ok(userService.updateUser(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {

        userService.deleteUser(id);

        return ResponseEntity.ok("User deleted successfully.");
    }

    @GetMapping("/department/{department}")
    public ResponseEntity<List<UserResponse>> getByDepartment(
            @PathVariable("department") String department) {

        return ResponseEntity.ok(
                userService.getUsersByDepartment(department));
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserResponse>> getByRole(
            @PathVariable("role") String role) {

        return ResponseEntity.ok(
                userService.getUsersByRole(role));
    }

    @GetMapping("/status/{active}")
    public ResponseEntity<List<UserResponse>> getByStatus(
            @PathVariable("active") Boolean active) {

        return ResponseEntity.ok(
                userService.getUsersByStatus(active));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getByEmail(
            @PathVariable("email") String email) {

        return ResponseEntity.ok(
                userService.getUserByEmail(email));
    }
}