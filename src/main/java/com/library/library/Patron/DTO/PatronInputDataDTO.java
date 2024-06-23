package com.library.library.Patron.DTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.security.core.parameters.P;

@Data
public class PatronInputDataDTO {
    @NotBlank(message = "Please Provide a Username")
    @Size(min = 4, max = 20, message = "Username must be at Least 4 char long , at most 20 char long")
    private String username;
    @NotBlank
    @NotBlank(message = "Please Provide an Email")
    @Email(message = "Not Valid input for Email occupation")
    private String email;
    @NotBlank(message = "Please Provide a PhoneNumber")
    @Size(min = 11, max = 12, message = "Not Valid Egyptian PhoneNumber (Must be 11 numbers)")
    @Pattern(regexp = "^\\d{11}$\n", message = "Invalid PhoneNumber (Contains Non-Digit chars)")
    private String phoneNumber;
    @NotBlank(message = "Please Provide a FirstName")
    private String firstName;
    private String lastName;
}
