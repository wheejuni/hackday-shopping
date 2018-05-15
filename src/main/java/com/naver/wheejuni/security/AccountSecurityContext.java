package com.naver.wheejuni.security;

import com.naver.wheejuni.domain.UserGroups;
import org.springframework.security.core.Authentication;

import java.util.Set;

public interface AccountSecurityContext extends Authentication {

    Set<UserGroups> getUsergroups();

    long getId();
}
