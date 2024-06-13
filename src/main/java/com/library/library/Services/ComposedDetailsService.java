package com.library.library.Services;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

import java.util.ArrayList;
import java.util.List;

@Service
@Data
public class ComposedDetailsService implements UserDetailsService {

    @Autowired
    private final LibrarianService librarianService;
    @Autowired
    private final PatronService patronService;

    private List<UserDetailsService> services;

    @PostConstruct
    public void setServices() {
        List<UserDetailsService> new_services = new ArrayList<>();
        new_services.add(this.patronService);
        new_services.add(this.librarianService);
        this.services = new_services;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        for (UserDetailsService service : services) {
            try {
                UserDetails user = service.loadUserByUsername(username);
                return user;
            } catch (UsernameNotFoundException e) {
                continue;
            }
        }
        throw new UsernameNotFoundException("User Not Found");
    }
}
