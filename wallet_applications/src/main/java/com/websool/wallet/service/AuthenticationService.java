package com.websool.wallet.service;

import com.websool.wallet.domain.request.LoginRequest;
import com.websool.wallet.domain.request.RegisterRequest;
import com.websool.wallet.domain.response.AuthResponse;
import org.springframework.stereotype.Service;

@Service
public interface AuthenticationService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
}
