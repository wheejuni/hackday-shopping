package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.domain.Account;
import com.naver.wheejuni.domain.UserNotificationInbox;
import com.naver.wheejuni.domain.events.article.NewArticleEvent;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface NotificationService {

    void sendNotification(NewArticleEvent event);

    void setNotificationsRead(long accountId, List<Long> notificationId);

    Flux<UserNotificationInbox> getNotificationEvent(long accountId);
}
