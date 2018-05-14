package com.naver.wheejuni.controller

import com.naver.wheejuni.dto.security.UserJoinRequest
import com.naver.wheejuni.dto.security.UseridVerification
import com.naver.wheejuni.dto.security.UseridVerificationRequest
import com.naver.wheejuni.service.specification.AccountService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/user")
open class AccountController(private val accountService: AccountService) {

    @PostMapping("/join")
    fun joinAccount(@RequestBody request:UserJoinRequest) {
        accountService.joinNewAccount(request)
    }

    @PostMapping("/checkid")
    fun checkUserid(@RequestBody request: UseridVerificationRequest): UseridVerification {
        return accountService.checkUserid(request.id)
    }
}