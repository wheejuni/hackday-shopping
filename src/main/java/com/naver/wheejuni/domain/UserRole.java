package com.naver.wheejuni.domain;

import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.NoSuchElementException;

public enum UserRole {

    USER("ROLE_USER"),
    ADMIN("ROLE_ADMIN");

    private String role;

    UserRole(String role) {
        this.role = role;
    }

    private boolean isCorrectRole(String role) {
        return role.equalsIgnoreCase(this.role);
    }

    public String getRoleName() {
        return role;
    }

    public SimpleGrantedAuthority toAuthority() {
        return new SimpleGrantedAuthority(this.role);
    }

    public static UserRole findRole(String role) {
        return Arrays.stream(UserRole.values()).filter(r -> r.isCorrectRole(role)).findFirst().orElseThrow(() -> new NoSuchElementException("이름에 맞는 권한이 없습니다."));
    }


}
