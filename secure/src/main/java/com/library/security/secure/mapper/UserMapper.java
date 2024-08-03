package com.library.security.secure.mapper;

import com.library.security.secure.dto.UserDto;
import com.library.security.secure.models.UserModel;
import com.library.security.secure.utlities.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserMapper {


    private final PasswordEncoder passwordEncoder;

    public UserModel mapToModel(UserDto userDto){
        return UserModel.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword())) /// we need here a password encoder
                .active(Boolean.TRUE)
                .enabled(Boolean.TRUE)
                .phoneNumber(userDto.getPhoneNumber())
                .roles(List.of(UserRole.ROLE_PATRON))
                .build();
    }
}
