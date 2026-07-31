
package com.omkar.inventory.auth.service;

import com.omkar.inventory.auth.dto.*;

public interface AuthService {

    void register(RegisterRequest request);

    LoginResponse login(LoginRequest request);
}