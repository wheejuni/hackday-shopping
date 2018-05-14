package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.dto.article.ArticleListRequest;
import com.naver.wheejuni.dto.article.ArticleUpdateRequest;
import com.naver.wheejuni.dto.article.NewArticleDto;
import com.naver.wheejuni.dto.article.PagedArticles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleService {

    Article saveNewArticle(NewArticleDto dto);

    Optional<Article> updateArticle(ArticleUpdateRequest request);

    List<Article> getByTargetGroups(Set<UserGroups> targetGroups);

    Page<Article> getByTargetGroupsPaged(Set<UserGroups> targetGroups, Pageable pageable);

    Article getByArticleId(long id);

    PagedArticles getPagedArticle(ArticleListRequest request, Set<UserGroups> userGroups);
}
