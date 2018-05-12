package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.dto.security.UserJoinRequest;

public interface AccountService {

    void joinNewAccount(UserJoinRequest request);
}
