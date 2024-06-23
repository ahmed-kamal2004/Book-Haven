package com.library.library.Patron.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
public class PatronDataDTO{
    private Integer id;
    private String username;
    private String email;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private boolean enabled;
}
