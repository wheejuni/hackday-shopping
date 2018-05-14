package com.naver.wheejuni.service.impl;

import com.naver.wheejuni.domain.repositories.jpa.AccountRepository;
import com.naver.wheejuni.dto.security.UserJoinRequest;
import com.naver.wheejuni.dto.security.UseridVerification;
import com.naver.wheejuni.service.specification.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void joinNewAccount(UserJoinRequest request) {
        accountRepository.save(request.toModel(passwordEncoder));
    }

    @Override
    public UseridVerification checkUserid(String userid) {
        return new UseridVerification(userid, accountRepository.findByUserId(userid).isPresent());
    }
}
