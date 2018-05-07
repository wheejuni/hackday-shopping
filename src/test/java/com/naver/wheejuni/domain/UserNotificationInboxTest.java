package com.naver.wheejuni.domain;

import com.google.common.collect.Sets;
import org.junit.Before;
import org.junit.Test;
import reactor.core.publisher.Flux;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserNotificationInboxTest {

    private UserNotificationInbox inbox;
    private Set<UserGroups> testGroups;

    @Before
    public void setUp() {
        this.inbox = new UserNotificationInbox();
        Set<UserGroups> groups = Sets.newHashSet();

        groups.add(UserGroups.A_GROUP);
        groups.add(UserGroups.B_GROUP);

        this.inbox.setListeningGroups(groups);

        this.testGroups = Sets.newHashSet();
        Flux.just(UserGroups.B_GROUP, UserGroups.C_GROUP).subscribe(g -> testGroups.add(g));
    }

    @Test
    public void inbox_matchingTargetGroup() {
        assertThat(inbox.isTargetInbox(testGroups), is(true));
    }

}