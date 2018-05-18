package com.naver.wheejuni.domain.repositories.jpa;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.File;
import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.dto.article.SingleArticle;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local-dev")
@Slf4j
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    @Before
    public void setUp() {
        Set<UserGroups> testGroups = Sets.newHashSet();
        Set<File> files = Sets.newHashSet();
        File mockfile = new File("fake path", "fake");


        Flux.just(UserGroups.B_GROUP, UserGroups.C_GROUP).subscribe(testGroups::add);
        Flux.just(mockfile, mockfile, mockfile).subscribe(files::add);


        Article article = Article.builder().title("test article").content("hi this is test.").userGroups(UserGroups.B_GROUP).files(files).build();
        repository.save(article);
    }

    @Test
    public void repository_inQueryMethod() throws JsonProcessingException {
        Set<UserGroups> queryGroups = Sets.newHashSet();
        queryGroups.add(UserGroups.A_GROUP);
        queryGroups.add(UserGroups.B_GROUP);

        assertThat(repository.findDistinctByUserGroupsIn(queryGroups).size(), is(1));
        assertThat(repository.findDistinctByUserGroupsIn(queryGroups).get(0).getFiles().size(), is(3));

        SingleArticle article = repository.findDistinctByUserGroupsIn(queryGroups).get(0).toDto();

        log.error(new ObjectMapper().writeValueAsString(article));

        assertThat(article.getFiles().size(), is(3));
    }

}