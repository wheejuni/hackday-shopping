package com.naver.wheejuni.service.impl;

import com.google.common.collect.Lists;
import com.naver.wheejuni.domain.Notification;
import com.naver.wheejuni.domain.UserNotificationInbox;
import com.naver.wheejuni.domain.events.article.NewArticleEvent;
import com.naver.wheejuni.domain.repositories.mongo.UserNotificationInboxRepository;
import com.naver.wheejuni.service.specification.NotificationService;
import com.naver.wheejuni.service.specification.UUIDGenerator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private UserNotificationInboxRepository repository;

    @Autowired
    private UUIDGenerator uuidGenerator;

    @Override
    public void sendNotification(NewArticleEvent event) {
        Notification notification = Notification.fromArticleEvent(event, uuidGenerator.generateUUID());

        List<UserNotificationInbox> processedInboxes = repository.findByListeningGroupsContains(event.getTargetGroups()).stream().map(inbox -> inbox.addNotification(notification)).collect(Collectors.toList());
        repository.saveAll(processedInboxes);
    }

    @Override
    public void setNotificationsRead(long accountid, List<String> notificationId) {
        UserNotificationInbox inbox = repository.findById(accountid);
        inbox.setNotificationsRead(notificationId);

        repository.save(inbox);
    }

}
