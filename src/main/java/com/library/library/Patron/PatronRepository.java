package com.library.library.Patron;

import com.library.library.Utilities.UserRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatronRepository extends UserRepository<Patron, Integer> {

    public Optional<Patron> findById(Integer id);

}
