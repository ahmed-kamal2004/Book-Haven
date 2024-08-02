package com.library.security.secure.dto;

import lombok.Data;

@Data
public class UserDto
{
    private String email;
    private String password;
    private String phoneNumber;
}
