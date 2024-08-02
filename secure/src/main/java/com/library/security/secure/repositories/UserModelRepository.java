package com.library.security.secure.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.library.security.secure.models.UserModel;
import org.springframework.stereotype.Repository;


@Repository
public interface UserModelRepository extends JpaRepository<UserModel, Integer> {
    Optional<UserDetails> findByEmail(String email);
}
