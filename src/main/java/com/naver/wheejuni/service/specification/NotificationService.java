package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.domain.events.article.NewArticleEvent;

public interface NotificationService {

    void sendNotification(NewArticleEvent event);
}
