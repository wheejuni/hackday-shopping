package com.naver.wheejuni.dto.article

import com.fasterxml.jackson.annotation.JsonProperty
import com.naver.wheejuni.domain.Article

data class ArticleListView(

        @field:JsonProperty("title")
        val title: String? = null,

        @field:JsonProperty("group")
        val group: String? = null,

        @field:JsonProperty("href")
        val link: String? = null) {

    companion object {
        fun fromModel(article: Article, link: String): ArticleListView {
            return ArticleListView(title = article.title, link = link)
        }
    }
}

