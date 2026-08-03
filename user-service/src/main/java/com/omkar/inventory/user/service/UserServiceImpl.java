package com.omkar.inventory.user.service;

import com.omkar.inventory.user.dto.UserRequest;
import com.omkar.inventory.user.dto.UserResponse;
import com.omkar.inventory.user.entity.User;
import com.omkar.inventory.user.repository.UserRepository;
import com.omkar.inventory.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public UserResponse createUser(UserRequest request) {

        User user = User.builder()
                .employeeCode(request.getEmployeeCode())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(request.getPassword())
                .phone(request.getPhone())
                .department(request.getDepartment())
                .role(request.getRole())
                .active(request.getActive())
                .build();

        return mapToResponse(userRepository.save(user));
    }

    @Override
    public UserResponse getUserById(Long id) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    @Override
    public List<UserResponse> getAllUsers() {

        return userRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse updateUser(Long id, UserRequest request) {

        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setEmployeeCode(request.getEmployeeCode());
        user.setFirstName(request.getFirstName());
        user.setLastName(request.getLastName());
        user.setEmail(request.getEmail());
        user.setPassword(request.getPassword());
        user.setPhone(request.getPhone());
        user.setDepartment(request.getDepartment());
        user.setRole(request.getRole());
        user.setActive(request.getActive());

        return mapToResponse(userRepository.save(user));
    }

    @Override
    public void deleteUser(Long id) {

        userRepository.deleteById(id);
    }

    @Override
    public List<UserResponse> getUsersByDepartment(String department) {

        return userRepository.findByDepartment(department)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getUsersByRole(String role) {

        return userRepository.findByRole(role)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<UserResponse> getUsersByStatus(Boolean active) {

        return userRepository.findByActive(active)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponse(user);
    }

    private UserResponse mapToResponse(User user) {

        return UserResponse.builder()
                .id(user.getId())
                .employeeCode(user.getEmployeeCode())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phone(user.getPhone())
                .department(user.getDepartment())
                .role(user.getRole())
                .active(user.getActive())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}