package com.naver.wheejuni.domain.repositories.jpa;

import com.naver.wheejuni.domain.Article;
import org.springframework.data.repository.CrudRepository;

public interface ArticleRepository extends CrudRepository<Article, Long> {
}
