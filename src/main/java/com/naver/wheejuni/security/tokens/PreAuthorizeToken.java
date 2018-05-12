package com.naver.wheejuni.security.tokens;

import com.naver.wheejuni.dto.security.UserLoginRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class PreAuthorizeToken extends UsernamePasswordAuthenticationToken {

    private PreAuthorizeToken(String userid, String password) {
        super(userid, password);
    }

    public PreAuthorizeToken(UserLoginRequest request) {
        this(request.getId(), request.getPassword());
    }

    public String getUserId() {
        return (String)super.getPrincipal();
    }

    public String getPassword() {
        return (String)super.getCredentials();
    }

}
