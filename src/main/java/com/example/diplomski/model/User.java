package com.example.diplomski.model;

import com.example.diplomski.enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name = "USERS")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
public abstract class User implements UserDetails {

    @Id
    @Column(unique = true, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private boolean emailVerified = false;

    private String password;

    @Column(unique = true)
    private String verificationCode;

    @Enumerated(EnumType.STRING)
    private Role role;

    @CreationTimestamp
    private Instant created;

    @UpdateTimestamp
    private Instant modified;

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return emailVerified;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(this.role.toAuthority());
    }
}