package com.websool.wallet.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginRequest {
    @NotBlank(message = "Email is required!")
    @Email(message = "Please enter valid email!")
    private String email;

    @NotBlank(message = "Password is required!")
    private String password;
}
