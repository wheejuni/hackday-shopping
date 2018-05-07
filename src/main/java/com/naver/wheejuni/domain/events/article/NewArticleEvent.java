package com.naver.wheejuni.domain.events.article;


import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.NotificationTypes;

public class NewArticleEvent extends ArticleEvent {

    private NewArticleEvent(Article article) {
        super(article, NotificationTypes.CREATE);
    }

    public static NewArticleEvent ofArticle(Article article) {
        return new NewArticleEvent(article);
    }
}
