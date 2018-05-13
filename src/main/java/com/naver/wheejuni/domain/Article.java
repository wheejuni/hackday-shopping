package com.naver.wheejuni.domain;

import com.google.common.collect.Lists;
import com.naver.wheejuni.dto.article.ArticleUpdateRequest;
import com.naver.wheejuni.dto.article.NewArticleDto;
import com.naver.wheejuni.dto.fileupload.FileUploadResult;
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
    @ElementCollection(fetch = FetchType.EAGER)
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
                .files(mapRequestToFiles(dto))
                .build();
    }

    private static List<File> mapRequestToFiles(NewArticleDto dto) {
        if(dto.getFile() == null) {
            return Lists.newArrayList();
        }
        return dto.getFile().stream().map(FileUploadResult::toModel).collect(Collectors.toList());
    }

}
