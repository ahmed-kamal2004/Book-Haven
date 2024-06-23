package com.library.library.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.security.core.userdetails.UserDetails;

@NoRepositoryBean
public interface UserRepository<T, ID> extends JpaRepository<T, ID> {
    Optional<UserDetails> findByUsername(String username);

    Optional<UserDetails> findByEmail(String email);
}
