package com.naver.wheejuni.domain.repositories.jpa;

import com.naver.wheejuni.domain.Account;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AccountRepository extends CrudRepository<Account, Long> {

    Optional<Account> findByUserId(String userId);
}
