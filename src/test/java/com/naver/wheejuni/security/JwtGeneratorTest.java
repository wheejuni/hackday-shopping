package com.naver.wheejuni.security;

import com.google.common.collect.Sets;
import com.naver.wheejuni.domain.Account;
import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.domain.UserRole;
import com.naver.wheejuni.security.tokens.PostAuthorizeToken;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.Set;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local-dev")
public class JwtGeneratorTest {

    @Autowired
    private JwtGenerator jwtGenerator;

    private PostAuthorizeToken token;
    private Set<UserGroups> groups = Sets.newHashSet();

    @Before
    public void setUp() {
        Flux.just(UserGroups.B_GROUP, UserGroups.C_GROUP).subscribe(this.groups::add);

        this.token = mock(PostAuthorizeToken.class);
        when(token.getAccount()).thenReturn(Account.builder().role(UserRole.USER).id(1L).userGroups(this.groups).build());
    }

    @Test
    public void generator_generateUserInfoJWT() {
        log.error(jwtGenerator.generateJWT(this.token));
    }

}