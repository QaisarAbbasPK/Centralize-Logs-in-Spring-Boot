package com.websool.wallet.domain.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequest {

    @NotBlank(message = "Your good name is required!")
    private String fullName;

    @NotBlank(message = "Email is required!")
    @Email(message = "Please enter valid email!")
    private String email;

    @Pattern(regexp = "[0-9]{10}", message = "Mobile No is Invalid")
    private String mobileNumber;

    @NotBlank(message = "Password is required!")
    private String password;

    @NotBlank(message = "Confirm password is required!")
    private String confirmPassword;
}
