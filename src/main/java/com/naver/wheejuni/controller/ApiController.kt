package com.naver.wheejuni.controller

import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1")
open class ApiController {

    @GetMapping("/hi", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sayHi(): String {
        return "hi"
    }
}