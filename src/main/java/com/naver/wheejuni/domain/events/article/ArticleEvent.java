package com.naver.wheejuni.domain.events.article;

import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.NotificationTypes;
import com.naver.wheejuni.domain.UserGroups;
import lombok.Data;
import org.springframework.context.ApplicationEvent;

import java.util.Set;

@Data
public class ArticleEvent extends ApplicationEvent {

    private Set<UserGroups> targetGroups;
    private String title;
    private long articleId;
    private NotificationTypes notificationTypes;

    protected ArticleEvent(Article article, NotificationTypes types) {
        super(article);
        this.targetGroups = article.getUserGroups();
        this.title = article.getTitle();
        this.notificationTypes = types;
    }
}
