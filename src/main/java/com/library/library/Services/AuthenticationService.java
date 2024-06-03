package com.library.library.Services;


import com.library.library.Authentication.JwtService;
import com.library.library.Models.AuthRequest;
import com.library.library.Models.AuthResponse;
import com.library.library.Models.SignUpRequest;
import com.library.library.Models.SignUpResponse;
import com.library.library.ModelsDB.Librarian;
import com.library.library.ModelsDB.Patron;
import com.library.library.Repositories.LibrarianRepository;
import com.library.library.Repositories.PatronRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final LibrarianRepository librarianRepository;
    private final PatronRepository patronRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthResponse loginLibrarian(AuthRequest credentials){

        try{
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword()
                    )
            );
            Optional<UserDetails> user = this.librarianRepository.findByUsername(credentials.getUsername());
            if(user.isPresent()){
                return AuthResponse.builder()
                        .done(true)
                        .token(this.jwtService.generateToken(user.get()))
                        .build();
            }
            return AuthResponse.builder()
                    .build();

        }
        catch(AuthenticationException e){
            return AuthResponse.builder().build();
        }

    }
    public SignUpResponse registerLibrarian(SignUpRequest user) {
        if(this.librarianRepository.findByUsername(user.getUsername()).isPresent()){
            return SignUpResponse.builder()
                    .msg("Username "+user.getUsername()+" Already Exists")
                    .done(false)
                    .build();
        }
        if(this.librarianRepository.findByEmail(user.getEmail()).isPresent()){
            return SignUpResponse.builder()
                    .msg("Email "+user.getEmail()+" Already Exists")
                    .done(false)
                    .build();
        }

        Librarian registeredLibrarian = Librarian.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .password(this.passwordEncoder.encode(user.getPassword()))
                .build();

        this.librarianRepository.save(registeredLibrarian);

        return SignUpResponse.builder()
                .msg("Username " + user.getUsername() + "Created SuccessFully")
                .done(true)
                .build();

    }
    public SignUpResponse registerPatron(SignUpRequest user){
        if(this.patronRepository.findByUsername(user.getUsername()).isPresent()){
            return SignUpResponse.builder()
                    .msg("Username "+user.getUsername()+" Already Exists")
                    .done(false)
                    .build();
        }
        if(this.patronRepository.findByEmail(user.getEmail()).isPresent()){
            return SignUpResponse.builder()
                    .msg("Email "+user.getEmail()+" Already Exists")
                    .done(false)
                    .build();
        }

        Patron registeredLibrarian = Patron.builder()
                .username(user.getUsername())
                .email(user.getEmail())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .password(this.passwordEncoder.encode(user.getPassword()))
                .build();

        this.patronRepository.save(registeredLibrarian);

        return SignUpResponse.builder()
                .msg("Username " + user.getUsername() + " Created SuccessFully")
                .done(true)
                .build();

    }
//    public AuthResponse patronLogin(AuthRequest credentials){
//
//    }

}
