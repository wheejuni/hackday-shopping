package com.naver.wheejuni.domain.repositories.jpa;

import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.UserGroups;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Set;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    List<Article> findDistinctByUserGroupsIn(Set<UserGroups> groups);

    Page<Article> findDistinctByUserGroupsIn(Set<UserGroups> groups, Pageable pageable);
}
