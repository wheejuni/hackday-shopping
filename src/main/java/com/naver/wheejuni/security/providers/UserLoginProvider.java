package com.naver.wheejuni.security.providers;

import com.naver.wheejuni.domain.Account;
import com.naver.wheejuni.domain.UserRole;
import com.naver.wheejuni.domain.repositories.jpa.AccountRepository;
import com.naver.wheejuni.exceptions.security.CredentialsNotMatchException;
import com.naver.wheejuni.security.tokens.PostAuthorizeToken;
import com.naver.wheejuni.security.tokens.PreAuthorizeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class UserLoginProvider implements AuthenticationProvider {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        PreAuthorizeToken token = (PreAuthorizeToken)authentication;

        Account account = fetchAccount(token);
        if(account.isCorrectPassword(token.getPassword(), passwordEncoder)) {
            return new PostAuthorizeToken(account, parseAuthorities(account));
        }
        throw new CredentialsNotMatchException();
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return PreAuthorizeToken.class.isAssignableFrom(aClass);
    }

    private Account fetchAccount(PreAuthorizeToken token) {
        return accountRepository.findByUserId(token.getUserId()).orElseThrow(CredentialsNotMatchException::new);
    }

    private List<SimpleGrantedAuthority> parseAuthorities(Account account) {
        return Stream.of(account.getRole()).map(UserRole::toAuthority).collect(Collectors.toList());
    }
}
