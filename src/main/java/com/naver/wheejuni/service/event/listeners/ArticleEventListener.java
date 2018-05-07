package com.naver.wheejuni.service.event.listeners;

import com.naver.wheejuni.domain.events.article.ArticleEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class ArticleEventListener implements ApplicationListener<ArticleEvent> {

    @Override
    public void onApplicationEvent(ArticleEvent event) {

    }
}
