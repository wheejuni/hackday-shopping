package com.naver.wheejuni.domain;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@Slf4j
public class NotificationTest {

    private static final String INTENDED_MESSAGE_CREATED = "새 공지사항이 등록되었습니다: 안녕하세요";

    private Notification notification;

    @Before
    public void setUp() {
        this.notification = new Notification();

        this.notification.setTitle("안녕하세요");
        this.notification.setTypes(NotificationTypes.CREATE);
    }

    @Test
    public void notification_returnCaptionAsMapped() {
        log.debug("{}", this.notification.getId());
        String caption = this.notification.getNotificationMessage();

        assertThat(caption, is(INTENDED_MESSAGE_CREATED));
    }

}