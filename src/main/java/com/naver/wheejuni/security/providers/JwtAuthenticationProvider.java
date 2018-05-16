package com.naver.wheejuni.security.providers;

import com.naver.wheejuni.security.AccountContext;
import com.naver.wheejuni.security.JwtDecoder;
import com.naver.wheejuni.security.tokens.JwtPreAuthorizeToken;
import com.naver.wheejuni.security.tokens.PostAuthorizeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider implements AuthenticationProvider {

    private JwtDecoder decoder;

    @Autowired
    public JwtAuthenticationProvider(JwtDecoder decoder) {
        this.decoder = decoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        JwtPreAuthorizeToken token = (JwtPreAuthorizeToken)authentication;

        AccountContext context = decoder.decodeJwt(token.getToken());
        return new PostAuthorizeToken(context);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return JwtPreAuthorizeToken.class.isAssignableFrom(aClass);
    }
}
