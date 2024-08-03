package com.library.security.secure.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data@AllArgsConstructor@NoArgsConstructor
public class ResponseDto {
    private HttpStatus statusCode;
    private String tokenMessage;
}
