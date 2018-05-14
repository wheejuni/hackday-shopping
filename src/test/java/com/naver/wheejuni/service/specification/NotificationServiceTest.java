package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.domain.Notification;
import com.naver.wheejuni.domain.UserNotificationInbox;
import com.naver.wheejuni.domain.repositories.mongo.UserNotificationInboxRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import reactor.core.publisher.Flux;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.notNull;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
@ActiveProfiles("local-dev")
public class NotificationServiceTest {

    @Autowired
    private NotificationService notificationService;

    @Autowired
    private UserNotificationInboxRepository repository;

    @Before
    public void setUp() {
        ReflectionTestUtils.setField(notificationService, "uuidGenerator", generateTestableUUID());
        UserNotificationInbox inbox = new UserNotificationInbox();

        inbox.setId(1L);
        Notification notification = new Notification();
        notification.setId(1L);

        inbox.addNotification(notification);
        repository.save(inbox).subscribe();

    }

    @Test
    public void notificationService_checkRead() {
        notificationService.setNotificationsRead(1L, Arrays.asList(1L));

        assertThat(repository.findById(1L).block().getUnreadNotificationsCount(), is(0L));
    }

    @Test
    public void notificationService_notifyWithDelay() {
//        assertThat(notificationService.getNotificationEvent(1L).getId(), is(1L));
    }

    @Bean
    private UUIDGenerator generateTestableUUID() {
        return () -> {
          return 1L;
        };
    }
}