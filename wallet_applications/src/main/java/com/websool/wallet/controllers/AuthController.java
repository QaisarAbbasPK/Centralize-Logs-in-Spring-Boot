package com.websool.wallet.controllers;

import com.websool.wallet.domain.request.LoginRequest;
import com.websool.wallet.domain.request.RegisterRequest;
import com.websool.wallet.domain.response.ApiResponse;
import com.websool.wallet.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("register")
    public ApiResponse register(@RequestBody @Valid RegisterRequest request){
        return new ApiResponse(authenticationService.register(request));
    }

    @PostMapping("login")
    public ApiResponse login(@RequestBody @Valid LoginRequest request){
        return new ApiResponse(authenticationService.login(request));
    }
}
