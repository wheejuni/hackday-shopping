package com.naver.wheejuni.controller

import com.naver.wheejuni.domain.Article
import com.naver.wheejuni.domain.repositories.mongo.UserNotificationInboxRepository
import com.naver.wheejuni.dto.article.NewArticleDto
import com.naver.wheejuni.service.specification.ArticleService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1")
open class ApiController(private val articleService: ArticleService) {

    @GetMapping("/hi", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sayHi(): String {
        return "hi"
    }

    @PostMapping("/article")
    fun postArticle(@RequestBody dto:NewArticleDto): Article {
        println(dto.groups)
        return articleService.saveNewArticle(dto)
    }
}