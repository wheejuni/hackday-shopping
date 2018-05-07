package com.naver.wheejuni.service.event.publishers;

import com.naver.wheejuni.domain.events.article.ArticleEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class ArticleEventPublisher{

    @Autowired
    private ApplicationEventPublisher publisher;

    public void publishEvent(ArticleEvent event) {
        publisher.publishEvent(event);
    }
}
