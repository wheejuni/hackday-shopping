package com.naver.wheejuni.service.event.listeners;

import com.naver.wheejuni.domain.Notification;
import com.naver.wheejuni.domain.events.article.ArticleEvent;
import com.naver.wheejuni.domain.events.article.NewArticleEvent;
import com.naver.wheejuni.service.specification.NotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArticleEventListener implements ApplicationListener<ArticleEvent> {

    @Autowired
    private NotificationService notificationService;

    @Override
    public void onApplicationEvent(ArticleEvent event) {
        log.error("received new article event");

        if(event instanceof NewArticleEvent) {
            notificationService.sendNotification((NewArticleEvent)event);
        }
    }
}
