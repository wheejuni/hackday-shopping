package com.naver.wheejuni.domain.repositories.jpa;

import com.google.common.collect.Sets;
import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.UserGroups;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;

import java.util.Set;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("local-dev")
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    @Before
    public void setUp() {
        Set<UserGroups> testGroups = Sets.newHashSet();

        Flux.just(UserGroups.B_GROUP, UserGroups.C_GROUP).subscribe(testGroups::add);

        Article article = Article.builder().title("test article").content("hi this is test.").userGroups(testGroups).build();
        repository.save(article);
    }

    @Test
    public void repository_inQueryMethod() {
        Set<UserGroups> queryGroups = Sets.newHashSet();
        queryGroups.add(UserGroups.A_GROUP);
        queryGroups.add(UserGroups.B_GROUP);

        assertThat(repository.findByUserGroupsIn(queryGroups).size(), is(1));
    }

}