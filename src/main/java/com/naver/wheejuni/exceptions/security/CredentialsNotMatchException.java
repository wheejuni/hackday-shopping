package com.naver.wheejuni.exceptions.security;

import org.springframework.security.core.AuthenticationException;

public class CredentialsNotMatchException extends AuthenticationException {

    private static final String DEFAULT_CRED_ERR_MSG = "ID 혹은 비밀번호가 맞지 않습니다.";

    public CredentialsNotMatchException() {
        super(DEFAULT_CRED_ERR_MSG);
    }

    public CredentialsNotMatchException(String msg) {
        super(msg);
    }
}
