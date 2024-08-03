package com.library.security.secure.mapper;

import com.library.security.secure.dto.UserDto;
import com.library.security.secure.models.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserMapper {

    private UserMapper(){};

    @Autowired
    private static PasswordEncoder passwordEncoder;
    public static UserModel mapToModel(UserDto userDto){
        return UserModel.builder()
                .email(userDto.getEmail())
                .password(passwordEncoder.encode(userDto.getPassword())) /// we need here a password encoder
                .active(true)
                .enabled(true)
                .phoneNumber(userDto.getPhoneNumber())
                .build();
    }
}
