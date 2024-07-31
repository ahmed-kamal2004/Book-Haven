package com.library.security.secure.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.library.security.secure.repositories.UserModelRepository;
import com.library.security.secure.utlities.Constants;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserModelService implements UserDetailsService {

    private final UserModelRepository modelRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return modelRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(Constants.USER_NOT_FOUND_EXCEPTION));
    }

}
