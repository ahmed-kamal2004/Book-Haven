package com.library.library.Repositories;

import com.library.library.ModelsDB.Patron;
import org.springframework.stereotype.Repository;

@Repository
public interface PatronRepository extends UserRepository<Patron, Integer> {
}
