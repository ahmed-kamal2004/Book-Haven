package com.library.security.secure.services;

import com.library.security.secure.exceptions.EmailNotFoundException;
import com.library.security.secure.repositories.UserModelRepository;
import com.library.security.secure.utlities.Constants;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAuthService implements UserDetailsService {

    private final UserModelRepository userModelRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userModelRepository.findByEmail(email)
                .orElseThrow(() -> new EmailNotFoundException(String.format(Constants.EMAIL_NOT_FOUND_EXCEPTION,email)));
    }
}
