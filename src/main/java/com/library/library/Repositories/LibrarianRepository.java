package com.library.library.Repositories;


import com.library.library.ModelsDB.Librarian;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LibrarianRepository extends JpaRepository<Librarian,Integer> {
    Optional<UserDetails> findByUsername(String username);
    Optional<UserDetails> findByEmail(String email);
}
