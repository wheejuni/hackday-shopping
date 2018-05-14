package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.dto.security.UserJoinRequest;
import com.naver.wheejuni.dto.security.UseridVerification;

public interface AccountService {

    void joinNewAccount(UserJoinRequest request);

    UseridVerification checkUserid(String userid);
}
