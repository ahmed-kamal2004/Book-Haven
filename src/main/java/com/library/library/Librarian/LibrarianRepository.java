package com.library.library.Repositories;

import com.library.library.ModelsDB.Librarian;
import org.springframework.stereotype.Repository;

@Repository
public interface LibrarianRepository extends UserRepository<Librarian, Integer> {
}
