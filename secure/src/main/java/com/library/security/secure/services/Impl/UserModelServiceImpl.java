package com.library.security.secure.services.Impl;

import com.library.security.secure.dto.LogInDto;
import com.library.security.secure.dto.UserDto;
import com.library.security.secure.exceptions.EmailAlreadyExistException;
import com.library.security.secure.exceptions.EmailNotFoundException;
import com.library.security.secure.jwt.IJwtService;
import com.library.security.secure.mapper.UserMapper;
import com.library.security.secure.models.UserModel;
import com.library.security.secure.repositories.UserModelRepository;
import com.library.security.secure.services.IUserModelService;
import com.library.security.secure.utlities.Constants;
import com.library.security.secure.utlities.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserModelServiceImpl implements IUserModelService {


    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;
    private final IJwtService jwtService;

    private final UserModelRepository userModelRepository;

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
    public String logIn(LogInDto logInDto) throws EmailNotFoundException {

        Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        logInDto.getEmail(),
                        logInDto.getPassword()
                )
        );

        List<String> lista = auth.getAuthorities().stream().map(Object::toString).toList();

//        System.out.println(lista);
//
//        for(var v:lista){
//            System.out.println(v);
//        }

        String token = this.jwtService.generateToken(auth.getName(),lista);

        System.out.println(token);

        return token;
    }

}
