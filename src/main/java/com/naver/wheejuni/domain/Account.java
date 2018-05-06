package com.naver.wheejuni.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
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

}
