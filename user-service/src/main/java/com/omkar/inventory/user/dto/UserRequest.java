package com.omkar.inventory.user.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserRequest {

    private String employeeCode;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String phone;

    private String department;

    private String role;

    private Boolean active;
}