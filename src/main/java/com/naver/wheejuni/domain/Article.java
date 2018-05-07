package com.naver.wheejuni.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Data
@Builder
public class Article extends BaseEntity{

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "ARTICLE_TITLE")
    private String title;

    @Column(name = "ARTICLE_CONTENT", columnDefinition = "TEXT NULL")
    private String content;

    @ElementCollection(targetClass = UserGroups.class)
    @Enumerated(value = EnumType.STRING)
    private Set<UserGroups> userGroups;

}
