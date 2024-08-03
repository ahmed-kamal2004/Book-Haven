package com.library.security.secure.services;

import com.library.security.secure.dto.UserDto;
import com.library.security.secure.exceptions.EmailAlreadyExistException;
import com.library.security.secure.exceptions.EmailNotFoundException;

public interface IUserModelService {

    public void signUp(UserDto userDto) throws EmailAlreadyExistException;
    public String logIn(UserDto userDto) throws EmailNotFoundException;
}
