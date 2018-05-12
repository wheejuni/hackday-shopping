package com.naver.wheejuni.service.specification;

import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.dto.ArticleUpdateRequest;
import com.naver.wheejuni.dto.NewArticleDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ArticleService {

    void saveNewArticle(NewArticleDto dto);

    Optional<Article> updateArticle(ArticleUpdateRequest request);

    List<Article> getByTargetGroups(Set<UserGroups> targetGroups);

    Page<Article> getByTargetGroupsPaged(Set<UserGroups> targetGroups, Pageable pageable);
}
