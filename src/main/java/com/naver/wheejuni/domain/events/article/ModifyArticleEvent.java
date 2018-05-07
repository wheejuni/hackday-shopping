package com.naver.wheejuni.domain.events.article;

import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.NotificationTypes;

public class ModifyArticleEvent extends ArticleEvent {

    private ModifyArticleEvent(Article article, NotificationTypes types) {
        super(article, types);
    }

    public static ModifyArticleEvent ofArticle(Article article) {
        return new ModifyArticleEvent(article, NotificationTypes.MODIFY);
    }
}
