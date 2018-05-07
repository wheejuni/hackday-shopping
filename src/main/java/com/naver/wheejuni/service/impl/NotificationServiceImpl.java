package com.naver.wheejuni.service.impl;

import com.naver.wheejuni.domain.events.article.NewArticleEvent;
import com.naver.wheejuni.service.specification.NotificationService;
import org.springframework.stereotype.Service;

@Service
public class NotificationServiceImpl implements NotificationService {

    @Override
    public void sendNotification(NewArticleEvent event) {

    }
}
