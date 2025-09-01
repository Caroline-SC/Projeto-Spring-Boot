package com.projetctJava.ProjectSpring.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserCreateRequest {

    @NotBlank(message = "Name is required")
    @Size(min = 5, max = 100, message = "Name must be between 2 and 100 characters long.")
    private String name;

    @NotBlank(message = "Email is required")
    @Email(message = "Email must be valid")
    private String email;

    @NotBlank(message = "PhoneNumber is required")
    @Pattern(regexp = "^[0-9]{9,11}$", message = "PhoneNumber must be valid")
    private String phoneNumber;

    @NotBlank(message = "Address is required")
    @Size(min = 10, max = 100, message = "Address must be between 10 and 100 characters long.")
    private String address;

}
