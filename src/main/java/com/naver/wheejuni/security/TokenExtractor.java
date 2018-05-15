package com.naver.wheejuni.security;

import com.naver.wheejuni.exceptions.security.InvalidJwtException;
import com.naver.wheejuni.security.tokens.JwtPreAuthorizeToken;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Component
public class TokenExtractor {

    private static final String HEADER_TYPE = "Authorization";
    public static final String HEADER_PREFIX = "Bearer ";

    public JwtPreAuthorizeToken extract(HttpServletRequest request) {
        String header = fetchTokenValue(request);

        if(StringUtils.isEmpty(header) | header.length() < HEADER_PREFIX.length()) {
            throw new InvalidJwtException("올바른 토큰 값이 아닙니다.");
        }

        return new JwtPreAuthorizeToken(header.substring(HEADER_PREFIX.length(), header.length()));
    }

    private String fetchTokenValue(HttpServletRequest request) {
        return request.getHeader(HEADER_TYPE);
    }
}
