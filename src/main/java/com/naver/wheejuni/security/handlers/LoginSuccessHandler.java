package com.naver.wheejuni.security.handlers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.naver.wheejuni.dto.security.UserLoginRequest;
import com.naver.wheejuni.dto.security.UserLoginResult;
import com.naver.wheejuni.exceptions.general.InternalServerError;
import com.naver.wheejuni.security.JwtGenerator;
import com.naver.wheejuni.security.tokens.PostAuthorizeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private JwtGenerator jwtGenerator;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse res, Authentication authentication) {
        PostAuthorizeToken token = (PostAuthorizeToken)authentication;
        String jwt = jwtGenerator.generateJWT(token);

        writeResponse(res, token, jwt);
    }

    private void writeResponse(HttpServletResponse res, PostAuthorizeToken token, String jwt) {
        res.setStatus(HttpStatus.OK.value());
        res.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);

        try {
            res.getWriter().write(new ObjectMapper().writeValueAsString(UserLoginResult.Companion.fromToken(token, jwt)));

        } catch (IOException e) {
            log.error("error occured during login attempt: {}", e.getMessage());
            throw new InternalServerError("서버 오류가 발생했습니다.", e);
        }
    }
}
