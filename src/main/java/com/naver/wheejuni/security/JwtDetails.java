package com.naver.wheejuni.security;

import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.domain.UserRole;

import java.util.List;
import java.util.Set;

public interface JwtDetails {

    String getUsername();

    Set<UserGroups> getUserGroups();

    List<UserRole> getRoles();

    long getUserid();
}
