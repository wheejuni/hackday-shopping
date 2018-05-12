package com.naver.wheejuni.controller

import com.naver.wheejuni.dto.security.UserJoinRequest
import com.naver.wheejuni.service.specification.AccountService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/user")
open class AccountController(private val accountService: AccountService) {

    @PostMapping("/join")
    fun joinAccount(@RequestBody request:UserJoinRequest) {
        accountService.joinNewAccount(request)
    }
}