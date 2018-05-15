package com.naver.wheejuni.controller

import com.naver.wheejuni.domain.UserGroups
import com.naver.wheejuni.security.AccountContext
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/info")
open class InfoController {

    @GetMapping("/available-groups")
    fun getGroups(): List<UserGroups> {
        return UserGroups.values().toList()
    }

    @GetMapping("/userinfo")
    @PreAuthorize("hasRole('ROLE_USER')")
    fun getUserinfo(authentication: Authentication): String {
        val context = authentication as AccountContext

        return context.name
    }
}