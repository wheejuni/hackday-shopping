package com.naver.wheejuni.security.tokens;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JwtPreAuthorizeToken extends UsernamePasswordAuthenticationToken {

    public JwtPreAuthorizeToken(String token) {
        super("token", token);
    }

    public String getToken() {
        return (String)super.getCredentials();
    }
}
