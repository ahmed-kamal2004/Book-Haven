package com.library.library.Models;


import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Builder
@Data
@Getter
@Setter
public class AuthResponse {
    private Boolean done;
    private String token;
}
