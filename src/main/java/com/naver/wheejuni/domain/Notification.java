package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.naver.wheejuni.domain.events.article.ArticleEvent;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Notification {

    @JsonProperty("NOTIFICATION_ID")
    private long id;

    @JsonProperty("NOTIFICATION_ARTICLE_TITLE")
    private String title;

    @JsonProperty("NOTIFICATION_TYPE")
    private NotificationTypes types;

    @JsonProperty("READ")
    private boolean read;

    public static Notification fromArticleEvent(ArticleEvent event, long generatedId) {
        return Notification.builder()
                .id(generatedId)
                .title(event.getTitle())
                .types(event.getNotificationTypes())
                .read(false)
                .build();
    }

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

    public boolean isMatchingId(Long id) {
        return id.equals(this.id);
    }

    public void setRead() {
        this.read = true;
    }

}
