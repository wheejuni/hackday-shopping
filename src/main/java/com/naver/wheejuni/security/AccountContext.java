package com.naver.wheejuni.security;

import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.domain.UserRole;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class AccountContext implements AccountSecurityContext {

    private String username;
    private Set<UserGroups> groups;
    private List<UserRole> roles;
    private long userId;

    public static AccountContext fromDecodedJwtDetails(JwtDetails details) {
        return AccountContext.builder()
                .username(details.getUsername())
                .groups(details.getUserGroups())
                .roles(details.getRoles())
                .userId(details.getUserid())
                .build();
    }

    @Override
    public Set<UserGroups> getUsergroups() {
        return this.groups;
    }

    @Override
    public long getId() {
        return this.userId;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream().map(r -> new SimpleGrantedAuthority(r.getRoleName())).collect(Collectors.toList());
    }

    @Override
    public Object getCredentials() {
        return this.userId;
    }

    @Override
    public Object getDetails() {
        return null;
    }

    @Override
    public Object getPrincipal() {
        return this;
    }

    @Override
    public boolean isAuthenticated() {
        return !this.roles.isEmpty();
    }

    @Override
    public void setAuthenticated(boolean b) throws IllegalArgumentException {

    }

    @Override
    public String getName() {
        return this.username;
    }
}
