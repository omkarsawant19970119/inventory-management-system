package com.omkar.inventory.user.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    private Long id;

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private String department;

    private String role;

    private Boolean active;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}