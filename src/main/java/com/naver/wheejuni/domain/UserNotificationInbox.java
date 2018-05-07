package com.naver.wheejuni.domain;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document
@Data
public class UserNotificationInbox {

    @Id
    private long id;

    private String title;
}
