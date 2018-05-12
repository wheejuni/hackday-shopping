package com.naver.wheejuni.domain;

import com.naver.wheejuni.dto.security.UserJoinRequest;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Account {

    @Id
    @GeneratedValue
    @Column(name = "ACCOUNT_ID")
    private long id;

    @Column(name = "LOGIN_ID")
    private String userId;

    private String password;

    @ElementCollection(targetClass = UserGroups.class)
    @Enumerated(value = EnumType.STRING)
    private Set<UserGroups> userGroups;

    @Enumerated(value = EnumType.STRING)
    private UserRole role;

    private Account(String userId, String password, Set<UserGroups> userGroups, UserRole role) {
        this.userId = userId;
        this.password = password;
        this.userGroups = userGroups;
        this.role = role;
    }

    public boolean isCorrectPassword(String password, PasswordEncoder encoder) {
        return encoder.matches(this.password, password);
    }

    public static Account fromRequest(UserJoinRequest req, PasswordEncoder passwordEncoder) {
        return new Account(req.getId(), passwordEncoder.encode(req.getPassword()), UserGroups.findMatchingGroups(req.getGroups()), UserRole.USER);
    }

}
