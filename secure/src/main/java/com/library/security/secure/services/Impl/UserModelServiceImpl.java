package com.library.security.secure.services.Impl;

import com.library.security.secure.dto.UserDto;
import com.library.security.secure.exceptions.EmailAlreadyExistException;
import com.library.security.secure.exceptions.EmailNotFoundException;
import com.library.security.secure.mapper.UserMapper;
import com.library.security.secure.models.UserModel;
import com.library.security.secure.repositories.UserModelRepository;
import com.library.security.secure.services.IUserModelService;
import com.library.security.secure.utlities.Constants;
import com.library.security.secure.utlities.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserModelServiceImpl implements UserDetailsService, IUserModelService {


    private final UserMapper userMapper;

    private final UserModelRepository userModelRepository;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userModelRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USER_NOT_FOUND_EXCEPTION));
    }

    @Override
    public void signUp(UserDto userDto) throws EmailAlreadyExistException {

        /// Check if email Already exists in the database or not
        this.userModelRepository.findByEmail(userDto.getEmail()).ifPresent( s ->
        {
            throw new EmailAlreadyExistException(userDto.getEmail());
        });

        UserModel userModel = userMapper.mapToModel(userDto);

        this.userModelRepository.save(userModel);
    }

    @Override
    public String logIn(UserDto userDto) throws EmailNotFoundException {
        return "Under Construction";
    }

}
