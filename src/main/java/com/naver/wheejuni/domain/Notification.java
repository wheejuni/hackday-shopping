package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Notification {

    @JsonProperty("NOTIFICATION_ARTICLE_TITLE")
    private String title;

    @JsonProperty("NOTIFICATION_TYPE")
    private NotificationTypes types;

    @JsonProperty("READ")
    private boolean read;

    @JsonProperty("message")
    public String getNotificationMessage() {
        StringBuilder sb = new StringBuilder();

        sb.append(types.getCaption());
        sb.append(this.title);

        return sb.toString();
    }

    public boolean isMatchingTitle(String title) {
        return title.equals(this.title);
    }

    public void setRead() {
        this.read = true;
    }

}
