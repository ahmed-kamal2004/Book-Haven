package com.library.security.secure.models;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.library.security.secure.utlities.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Entity(name = "user")
@Data
@AllArgsConstructor
@Builder
public class UserModel implements UserDetails {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    private Integer id;

    @Column(unique = true, nullable = false, updatable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    private String phoneNumber;
    @Column(insertable = false)
    private Boolean active;
    @Column(insertable = false)
    private Boolean enabled;
    private List<UserRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (UserRole role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.toString()));
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.active;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.active;
    }

    @Override
    public boolean isEnabled() {
        return this.enabled;
    }

}
