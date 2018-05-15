package com.naver.wheejuni.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.google.common.base.Strings;
import com.naver.wheejuni.domain.Account;
import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.security.tokens.PostAuthorizeToken;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtGenerator {

    @Value("${jwt.signingkey}")
    private String signingKey;

    public String generateJWT(PostAuthorizeToken token) {
        String generatedToken = Strings.nullToEmpty(null);
        Account account = token.getAccount();
        try {
            Algorithm algorithm = Algorithm.HMAC256(this.signingKey);
            String[] stringifiedGroups = new String[account.getUserGroups().size()];

            convertGroupToString(account.getUserGroups()).toArray(stringifiedGroups);

            generatedToken = JWT.create()
                    .withClaim("USERNAME", account.getName())
                    .withClaim("USER_ROLE", account.getRole().getRoleName())
                    .withClaim("USER_ID", account.getId())
                    .withArrayClaim("USER_TARGETGROUPS", stringifiedGroups)
                    .sign(algorithm);

        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return generatedToken;
    }

    private List<String> convertGroupToString(Set<UserGroups> groups) {
        return groups.stream().map(g -> g.getSymbol()).collect(Collectors.toList());
    }
}
