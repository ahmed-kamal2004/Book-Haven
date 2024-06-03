package com.library.library.Services;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
@Data
public class ComposedDetailsService implements UserDetailsService {


    private List<UserDetailsService> services;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for(UserDetailsService service:services){
            try{
                return service.loadUserByUsername(username);
            }
            catch (UsernameNotFoundException e){
                // Ignore and go to the next service
                continue;
            }
        }
        throw  new UsernameNotFoundException("User Not Found");
    }
}
