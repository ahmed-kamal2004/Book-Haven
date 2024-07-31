package com.library.library.Librarian;

import com.library.library.Utilities.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends UserRepository<Librarian, Integer> {
}
