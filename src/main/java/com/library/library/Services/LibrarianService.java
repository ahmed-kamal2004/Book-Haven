package com.library.library.Services;

import com.library.library.Exceptions.EmailNotFoundException;
import com.library.library.ModelsDB.Librarian;
import com.library.library.Repositories.LibrarianRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class LibrarianService implements UserDetailsService {

    private final LibrarianRepository librarianRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.librarianRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Librarian Not Found"));
    }
}
