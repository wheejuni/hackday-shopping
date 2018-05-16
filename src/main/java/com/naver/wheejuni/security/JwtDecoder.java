package com.naver.wheejuni.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.naver.wheejuni.domain.UserRole;
import com.naver.wheejuni.exceptions.security.InvalidJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class JwtDecoder {

    @Value("${jwt.signingkey}")
    private String signingKey;

    public AccountContext decodeJwt(String token) {
        DecodedJWT decodedJWT = isValidToken(token).orElseThrow(() -> new InvalidJwtException("유효한 토큰아 아닙니다."));
        return AccountContext.fromDecodedJwtDetails(DecodedJwtDetails.fromDecodedJWT(decodedJWT));
    }

    private Optional<DecodedJWT> isValidToken(String token) {

        DecodedJWT jwt = null;

        try {
            Algorithm algorithm = Algorithm.HMAC256(signingKey);
            JWTVerifier verifier = JWT.require(algorithm).build();

            jwt = verifier.verify(token);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Optional.ofNullable(jwt);
    }
}
