package com.naver.wheejuni.domain;

import com.naver.wheejuni.domain.converter.FileConverter;
import com.naver.wheejuni.dto.NewArticleDto;
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

    @Column(name = "ARTICLE_FILEID")
    @Convert(converter = FileConverter.class)
    private File file;

    @ElementCollection(targetClass = UserGroups.class)
    @Enumerated(value = EnumType.STRING)
    private Set<UserGroups> userGroups;

    public static Article fromDto(NewArticleDto dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userGroups(dto.getGroups())
                .build();
    }

}
