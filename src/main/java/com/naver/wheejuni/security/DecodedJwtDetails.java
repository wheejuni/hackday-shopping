package com.naver.wheejuni.security;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.domain.UserRole;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class DecodedJwtDetails implements JwtDetails {

    private String username;
    private Set<UserGroups> userGroups;
    private List<UserRole> roles;
    private long userId;

    public static DecodedJwtDetails fromDecodedJWT(DecodedJWT jwt) {
        return DecodedJwtDetails.builder()
                .username(jwt.getClaim("USERNAME").asString())
                .userGroups(new HashSet<UserGroups>(jwt.getClaim("USER_TARGETGROUPS").asList(UserGroups.class)))
                .roles(jwt.getClaim("USER_ROLE").asList(UserRole.class))
                .userId(jwt.getClaim("USER_ID").asLong())
                .build();
    }


    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public Set<UserGroups> getUserGroups() {
        return this.userGroups;
    }

    @Override
    public List<UserRole> getRoles() {
        return this.roles;
    }

    @Override
    public long getUserid() {
        return this.userId;
    }
}
