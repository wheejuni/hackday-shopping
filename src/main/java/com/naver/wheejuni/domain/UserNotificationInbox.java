package com.naver.wheejuni.domain;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.Set;

@Document
@Data
public class UserNotificationInbox {

    @Id
    private long id;

    private Set<UserGroups> listeningGroups = Sets.newHashSet();
    private List<Notification> notifications = Lists.newArrayList();

    public boolean isTargetInbox(Set<UserGroups> targetGroups) {
        return targetGroups.stream().anyMatch(g -> this.listeningGroups.contains(g));
    }

    public long getUnreadNotificationsCount() {
        return this.notifications.stream().filter(n -> !n.isRead()).count();
    }

    public void addNotification(Notification notification) {
        this.notifications.add(notification);
    }

}
