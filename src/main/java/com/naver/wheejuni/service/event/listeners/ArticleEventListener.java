package com.naver.wheejuni.service.event.listeners;

import com.naver.wheejuni.domain.events.article.ArticleEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ArticleEventListener implements ApplicationListener<ArticleEvent> {

    @Override
    public void onApplicationEvent(ArticleEvent event) {
        log.debug(event.toString());
    }
}
