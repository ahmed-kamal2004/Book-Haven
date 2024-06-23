package com.library.library.Authentication;

import com.library.library.Models.AuthRequest;
import com.library.library.Models.AuthResponse;
import com.library.library.Models.SignUpRequest;
import com.library.library.Models.SignUpResponse;
import com.library.library.Librarian.Librarian;
import com.library.library.Patron.Patron;
import com.library.library.Librarian.LibrarianRepository;
import com.library.library.Patron.PatronRepository;
import com.library.library.Utilities.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
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

        public AuthResponse loginLibrarian(AuthRequest credentials) {
                return this.authenticator(librarianRepository, credentials);
        }

        public AuthResponse loginPatron(AuthRequest credentials) {
                return this.authenticator(patronRepository, credentials);
        }

        public SignUpResponse registerLibrarian(SignUpRequest user) {

                SignUpResponse response = this.checkUsernameEmail(librarianRepository, user);

                if (response.isDone()) {
                        this.createLibrarian(user);
                        response.setMsg("Librarian with Username: " + user.getUsername() + " Created SuccessFully");
                }

                return response;

        }

        public SignUpResponse registerPatron(SignUpRequest user) {

                SignUpResponse response = this.checkUsernameEmail(patronRepository, user);

                if (response.isDone()) {
                        this.createPatron(user);
                        response.setMsg("Patron with Username: " + user.getUsername() + " Created SuccessFully");
                }

                return response;

        }

        private <T, ID> AuthResponse authenticator(UserRepository<T, ID> repository, AuthRequest credentials) {
                try {
                        authenticationManager.authenticate(
                                        new UsernamePasswordAuthenticationToken(
                                                        credentials.getUsername(),
                                                        credentials.getPassword()));
                        Optional<UserDetails> user = repository.findByUsername(credentials.getUsername());
                        if (user.isPresent()) {
                                return AuthResponse.builder()
                                                .done(true)
                                                .token(this.jwtService.generateToken(user.get()))
                                                .build();
                        }
                        return AuthResponse.builder()
                                        .done(false)
                                        .build();
                } catch (Exception e) {
                        System.out.println(e.getMessage());
                        return AuthResponse.builder()
                                        .done(false)
                                        .build();
                }
        }

        private void createPatron(SignUpRequest user) {
                Patron registeredPatron = Patron.builder()
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .phoneNumber(user.getPhoneNumber())
                                .password(this.passwordEncoder.encode(user.getPassword()))
                                .build();

                this.patronRepository.save(registeredPatron);

        }

        private void createLibrarian(SignUpRequest user) {
                Librarian registeredLibrarian = Librarian.builder()
                                .username(user.getUsername())
                                .email(user.getEmail())
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .phoneNumber(user.getPhoneNumber())
                                .password(this.passwordEncoder.encode(user.getPassword()))
                                .build();

                this.librarianRepository.save(registeredLibrarian);
        }

        private <T, ID> SignUpResponse checkUsernameEmail(UserRepository<T, ID> repository,
                        SignUpRequest user) {
                if (repository.findByUsername(user.getUsername()).isPresent()) {
                        return SignUpResponse.builder()
                                        .msg("Username " + user.getUsername() + " Already Exists")
                                        .done(false)
                                        .build();
                }
                if (repository.findByEmail(user.getEmail()).isPresent()) {
                        return SignUpResponse.builder()
                                        .msg("Email " + user.getEmail() + " Already Exists")
                                        .done(false)
                                        .build();
                }
                return SignUpResponse.builder()
                                .done(true)
                                .build();
        }

}
