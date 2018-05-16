package com.naver.wheejuni.domain;

import com.google.common.collect.Lists;
import com.naver.wheejuni.domain.events.article.NewArticleEvent;
import com.naver.wheejuni.dto.article.ArticleListView;
import com.naver.wheejuni.dto.article.ArticleUpdateRequest;
import com.naver.wheejuni.dto.article.NewArticleDto;
import com.naver.wheejuni.dto.article.SingleArticle;
import com.naver.wheejuni.dto.fileupload.FileUploadResult;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.DomainEvents;

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
    @Column(name = "ARTICLE_ID")
    private long id;

    @Column(name = "ARTICLE_TITLE")
    private String title;

    @Column(name = "ARTICLE_CONTENT", columnDefinition = "TEXT NULL")
    private String content;

    @Column(name = "ARTICLE_FILEID")
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "FILES", joinColumns = @JoinColumn(name = "ARTICLE_ID"))
    private List<File> files;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "AUTHOR_ACC_ID")
    private Account account;

    @Enumerated(value = EnumType.STRING)
    private UserGroups userGroups;

    public Article updateArticle(ArticleUpdateRequest request) {
        this.title = request.getTitle();
        this.content = request.getContent();
        this.files = request.getFile().stream().map(r -> r.toModel()).collect(Collectors.toList());

        return this;
    }

    public ArticleListView toListviewDto() {
        return new ArticleListView(this.title, this.getGroupSymbol(), String.valueOf(this.id));
    }

    public SingleArticle toDto() {
        return new SingleArticle(this.id, super.getStringifiedUpdatedTime(), this.title, this.content, getGroupSymbol(), this.files);
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public List<File> getFiles() {
        return files;
    }

    public Account getAccount() {
        return account;
    }

    public UserGroups getUserGroups() {
        return userGroups;
    }

    @DomainEvents
    NewArticleEvent sendEvent() {
        return NewArticleEvent.ofArticle(this);
    }

    public static Article fromDto(NewArticleDto dto) {
        return Article.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .userGroups(UserGroups.findMatchingGroupSingle(dto.getGroups()))
                .files(mapRequestToFiles(dto))
                .build();
    }

    private String getGroupSymbol() {
        return userGroups.getSymbol();
    }

    private static List<File> mapRequestToFiles(NewArticleDto dto) {
        if(dto.getFile() == null) {
            return Lists.newArrayList();
        }
        return dto.getFile().stream().map(FileUploadResult::toModel).collect(Collectors.toList());
    }

}
