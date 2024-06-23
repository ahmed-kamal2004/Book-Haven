package com.library.library.Services;

import com.library.library.Repositories.LibrarianRepository;
import com.library.library.Repositories.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PatronService implements UserDetailsService {

    private final PatronRepository patronRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.patronRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Patron Not Found"));
    }
}
