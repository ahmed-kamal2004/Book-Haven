package com.library.library.Repositories;

import com.library.library.Models.Patron;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer> {
    Optional<UserDetails> findByUsername(String username);
}
