package com.library.library.Models;


import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class SignUpResponse {
    private boolean done;
    private String msg;
}
