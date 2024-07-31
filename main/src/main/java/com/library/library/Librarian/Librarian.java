package com.library.library.Librarian;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;


@Table(name = "librarian")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Librarian implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column( name = "librarian_id")
    private Integer ID;
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String firstName;
    private String lastName;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
