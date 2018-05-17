package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserNotificationInbox {

    @Id
    private long id;

    private String name;

    private List<UserGroups> listeningGroups = Lists.newArrayList();
    private List<Notification> notifications = Lists.newArrayList();

    public boolean isTargetInbox(List<UserGroups> targetGroups) {
        return targetGroups.stream().anyMatch(g -> this.listeningGroups.contains(g));
    }

    public long getUnreadNotificationsCount() {
        return this.notifications.stream().filter(n -> !n.isRead()).count();
    }

    public long getId() {
        return id;
    }

    public List<UserGroups> getListeningGroups() {
        return listeningGroups;
    }

    public List<Notification> getNotifications() {
        return notifications;
    }

    public String getName() {
        if(this.name == null) {
            return "hello mongo";
        }

        return this.name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setListeningGroups(List<UserGroups> listeningGroups) {
        this.listeningGroups = listeningGroups;
    }

    public void setNotifications(List<Notification> notifications) {
        this.notifications = notifications;
    }

    public UserNotificationInbox addNotification(Notification notification) {
        this.notifications.add(notification);

        return this;
    }

    public UserNotificationInbox setNotificationsRead(List<String> notificationIds) {
        notificationIds.stream().forEach(i -> this.notifications.stream().filter(n -> n.isMatchingId(Long.parseLong(i))).findFirst().orElseThrow(() -> new NoSuchElementException("저장된 알림이 없습니다.")).setRead());

        return this;
    }

    public static UserNotificationInbox generateInbox(Account account) {
        return UserNotificationInbox.builder()
                .listeningGroups(convertGroupsToList(account))
                .notifications(Lists.newArrayList())
                .id(account.getId())
                .name(account.getName())
                .build();
    }

    private static List<UserGroups> convertGroupsToList(Account account) {
        List<UserGroups> groups = Lists.newArrayList();
         account.getUserGroups().stream().forEach(groups::add);

         return groups;
    }

}
