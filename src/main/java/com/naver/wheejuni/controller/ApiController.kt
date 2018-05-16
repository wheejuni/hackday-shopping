package com.naver.wheejuni.controller

import com.naver.wheejuni.domain.Article
import com.naver.wheejuni.domain.repositories.mongo.UserNotificationInboxRepository
import com.naver.wheejuni.dto.article.ArticleListRequest
import com.naver.wheejuni.dto.article.NewArticleDto
import com.naver.wheejuni.dto.article.PagedArticles
import com.naver.wheejuni.dto.article.SingleArticle
import com.naver.wheejuni.security.AccountContext
import com.naver.wheejuni.security.tokens.PostAuthorizeToken
import com.naver.wheejuni.service.specification.ArticleService
import lombok.extern.slf4j.Slf4j
import org.springframework.http.MediaType
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.security.Principal

@RestController
@RequestMapping("/api/v1")
open class ApiController(private val articleService: ArticleService) {

    @GetMapping("/hi", produces = [MediaType.APPLICATION_JSON_UTF8_VALUE])
    fun sayHi(): String {
        return "hi"
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    open fun adminTest(authentication: Authentication): String {
        return "hi"
    }

    @PostMapping("/article")
    open fun postArticle(@RequestBody dto:NewArticleDto): Article {
        println(dto.groups)
        return articleService.saveNewArticle(dto)
    }

    @GetMapping("/article")
    open fun getArticle(@RequestParam("id") id: String): SingleArticle {
        return articleService.getByArticleId(id.toLong())
    }

    @PostMapping("/articlelist")
    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    open fun getArticleList(@RequestBody articleListRequest: ArticleListRequest, authentication: Authentication): PagedArticles {
        val authenticationToken = authentication as PostAuthorizeToken
        return articleService.getPagedArticle(articleListRequest, authenticationToken.context.groups)
    }
}