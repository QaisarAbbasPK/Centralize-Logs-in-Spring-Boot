package com.websool.wallet.service.impl;

import com.websool.wallet.application.enums.RoleEnum;
import com.websool.wallet.data.entities.UsersEntity;
import com.websool.wallet.data.repositories.UserRepository;
import com.websool.wallet.domain.request.LoginRequest;
import com.websool.wallet.domain.request.RegisterRequest;
import com.websool.wallet.domain.response.AuthResponse;
import com.websool.wallet.exceptions.GlobalException;
import com.websool.wallet.service.AuthenticationService;
import com.websool.wallet.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse register(RegisterRequest request) {

        if (!request.getPassword().equals(request.getConfirmPassword())){
            throw new GlobalException("Your confirmed password not matched!");
        }else if (userRepository.findByEmail(request.getEmail()).isPresent()){
            throw new GlobalException("Your email already exist!");
        }

        UsersEntity user = UsersEntity.builder()
                .name(request.getFullName())
                .email(request.getEmail()).password(passwordEncoder.encode(request.getPassword()))
                .role(RoleEnum.USER).build();

        userRepository.save(user);

        var jwt = jwtService.generateToken(user);

        return AuthResponse.builder().token(jwt).build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder().token(jwt).build();
    }
}
