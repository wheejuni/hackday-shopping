package com.naver.wheejuni.security.filters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.wheejuni.dto.security.UserLoginRequest;
import com.naver.wheejuni.security.tokens.PostAuthorizeToken;
import com.naver.wheejuni.security.tokens.PreAuthorizeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserLoginFilter extends AbstractAuthenticationProcessingFilter {

    private AuthenticationSuccessHandler handler;

    protected UserLoginFilter(String defaultFilterProcessesUrl) {
        super(defaultFilterProcessesUrl);
    }

    public UserLoginFilter(String url, AuthenticationSuccessHandler handler) {
        super(url);
        this.handler = handler;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException, IOException, ServletException {
        return super.getAuthenticationManager().authenticate(extractToken(req));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req, HttpServletResponse res, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        PostAuthorizeToken token = (PostAuthorizeToken)authResult;


    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest req, HttpServletResponse res, AuthenticationException failed) throws IOException, ServletException {
        log.error(failed.getMessage());
    }

    private PreAuthorizeToken extractToken(HttpServletRequest req) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.readValue(req.getReader(), UserLoginRequest.class).toToken();
    }
}
