package com.naver.wheejuni.controller

import com.naver.wheejuni.domain.UserGroups
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
}