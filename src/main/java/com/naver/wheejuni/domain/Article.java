package com.naver.wheejuni.domain;

import com.naver.wheejuni.dto.ArticleUpdateRequest;
import com.naver.wheejuni.dto.NewArticleDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Article extends BaseEntity{

    @Id
    @GeneratedValue
    private long id;

    @Column(name = "ARTICLE_TITLE")
    private String title;

    @Column(name = "ARTICLE_CONTENT", columnDefinition = "TEXT NULL")
    private String content;

    @Column(name = "ARTICLE_FILEID")
    @ElementCollection
    private List<File> files;

    @ElementCollection(targetClass = UserGroups.class)
    @Enumerated(value = EnumType.STRING)
    private Set<UserGroups> userGroups;

    public Article updateArticle(ArticleUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.files = request.getFile().stream().map(r -> r.toModel()).collect(Collectors.toList());

        return this;
    }


    public static Article fromDto(NewArticleDto dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userGroups(dto.getGroups())
                .build();
    }

}
