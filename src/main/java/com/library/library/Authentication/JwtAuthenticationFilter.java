package com.library.library.Authentication;

import com.library.library.Repositories.PatronRepository;
import com.library.library.Services.PatronService;
import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final PatronService patronService;
    private final JwtService jwtService;
    @Override
    protected void doFilterInternal(
       @Nonnull HttpServletRequest request,
       @Nonnull HttpServletResponse response,
       @Nonnull FilterChain filterChain
    ) throws ServletException, IOException {
        // Implimentation
    }
}
