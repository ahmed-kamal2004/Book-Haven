package com.library.security.secure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data@AllArgsConstructor@NoArgsConstructor
public class UserDto
{
    private String email;
    private String password;
    private String phoneNumber;
}
