package com.library.security.secure.services;

import com.library.security.secure.dto.LogInDto;
import com.library.security.secure.dto.UserDto;
import com.library.security.secure.exceptions.EmailAlreadyExistException;
import com.library.security.secure.exceptions.EmailNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface IUserModelService{

    void signUp(UserDto userDto) throws EmailAlreadyExistException;
    String logIn(LogInDto logInDto) throws EmailNotFoundException;
}
