package com.library.library.Models;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Builder
@Data
@Getter
@Setter
public class SignUpRequest {

    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;

}
