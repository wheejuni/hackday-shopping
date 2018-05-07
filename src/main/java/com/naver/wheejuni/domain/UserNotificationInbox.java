package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.NoSuchElementException;
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

    @JsonProperty("new")
    public long getUnreadNotificationsCount() {
        return this.notifications.stream().filter(n -> !n.isRead()).count();
    }

    public UserNotificationInbox addNotification(Notification notification) {
        this.notifications.add(notification);

        return this;
    }

    public UserNotificationInbox setNotificationsRead(List<Long> notificationIds) {
        notificationIds.stream().forEach(i -> this.notifications.stream().filter(n -> n.isMatchingId(i)).findFirst().orElseThrow(() -> new NoSuchElementException("저장된 알림이 없습니다.")).setRead());

        return this;
    }

}
