package com.naver.wheejuni.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    public String getRoleName() {
        return role;
    }

    public SimpleGrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(this.role);
    }

}
