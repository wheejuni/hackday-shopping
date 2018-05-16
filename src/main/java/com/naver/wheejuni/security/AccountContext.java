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
public class AccountContext extends User {

    private String username;
    private Set<UserGroups> groups;
    private long userId;

    private AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities) {
        super(username, password, authorities);
    }

    public AccountContext(String username, String password, Collection<? extends GrantedAuthority> authorities, String username1, Set<UserGroups> groups, long userId) {
        super(username, password, authorities);
        this.username = username1;
        this.groups = groups;
        this.userId = userId;
    }

    public static AccountContext fromDecodedJwtDetails(JwtDetails details) {
        return new AccountContext(details.getUsername(), "1234", details.getRoles().stream().map(UserRole::toAuthority).collect(Collectors.toList()), details.getUsername(), details.getUserGroups(), details.getUserid());
    }

    public String getUsername() {
        return username;
    }

    public Set<UserGroups> getGroups() {
        return groups;
    }

    public long getUserId() {
        return userId;
    }
}
