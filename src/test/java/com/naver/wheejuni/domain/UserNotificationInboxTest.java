package com.naver.wheejuni.domain;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@Slf4j
public class UserNotificationInboxTest {

    private UserNotificationInbox inbox;
    private List<UserGroups> testGroups;

    @Before
    public void setUp() {
        this.inbox = new UserNotificationInbox();
        List<UserGroups> groups = Lists.newArrayList();

        groups.add(UserGroups.A_GROUP);
        groups.add(UserGroups.B_GROUP);

        this.inbox.setListeningGroups(groups);

        this.testGroups = Lists.newArrayList();
        Flux.just(UserGroups.B_GROUP, UserGroups.C_GROUP).subscribe(g -> testGroups.add(g));
    }

    @Test
    public void inbox_matchingTargetGroup() {
        assertThat(inbox.isTargetInbox(testGroups), is(true));
    }

    @Test
    public void inbox_addNotificationTest() throws Exception {
        Notification notification = new Notification();
        notification.setTypes(NotificationTypes.CREATE);
        notification.setTitle("안녕하세요");

        this.inbox = this.inbox.addNotification(notification);

        log.debug(new ObjectMapper().writeValueAsString(this.inbox));
        assertThat(inbox.getNotifications().size(), is(1));
    }

}