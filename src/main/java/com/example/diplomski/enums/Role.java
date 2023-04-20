package com.example.diplomski.enums;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum Role {
    REGULAR_USER,
    NUTRITIONIST;

    public SimpleGrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(this.name());
    }
}