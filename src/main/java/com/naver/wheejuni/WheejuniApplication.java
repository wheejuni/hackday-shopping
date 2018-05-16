package com.naver.wheejuni;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.naver.wheejuni.domain.*;
import com.naver.wheejuni.domain.repositories.jpa.AccountRepository;
import com.naver.wheejuni.domain.repositories.jpa.ArticleRepository;
import com.naver.wheejuni.domain.repositories.mongo.UserNotificationInboxRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.naver.wheejuni.domain.repositories.jpa")
@EnableReactiveMongoRepositories(basePackages = "com.naver.wheejuni.domain.repositories.mongo")
public class WheejuniApplication {

	public static void main(String[] args) {
		SpringApplication.run(WheejuniApplication.class, args);
	}

	@Bean
	CommandLineRunner getData(ArticleRepository articleRepository, UserNotificationInboxRepository inboxRepository) {
		return args -> {
			articleRepository.save(Article.builder().title("hey").content("hi").build());

			UserNotificationInbox inbox = new UserNotificationInbox();

			inbox.setId(1L);

			inboxRepository.deleteAll().thenMany(Flux.just(inbox).flatMap(inboxRepository::save)).subscribe(System.out::println);

		};
	}

	@Bean
	CommandLineRunner bootStrapAccounts(AccountRepository repository, PasswordEncoder passwordEncoder, ArticleRepository articleRepository) {
		return (String... args) -> {
			String encodedPassword = passwordEncoder.encode("1234");
			System.out.println(encodedPassword);
            Account account = Account.builder()
                    .role(UserRole.USER)
                    .password(encodedPassword)
                    .userId("emalyun@naver.com")
                    .name("이말년")
                    .userGroups(Stream.of(UserGroups.B_GROUP, UserGroups.C_GROUP).collect(Collectors.toSet()))
                    .build();

			repository.save(account);

			List<Article> articleList= Stream.of(Article.builder().title("hi").userGroups(Stream.of(UserGroups.B_GROUP, UserGroups.C_GROUP).collect(Collectors.toSet())).build(),
					Article.builder().title("hi").userGroups(Stream.of(UserGroups.B_GROUP, UserGroups.C_GROUP).collect(Collectors.toSet())).build(),
					Article.builder().title("hi").userGroups(Stream.of(UserGroups.B_GROUP, UserGroups.A_GROUP).collect(Collectors.toSet())).build(),
					Article.builder().title("hi").userGroups(Stream.of(UserGroups.B_GROUP, UserGroups.C_GROUP).collect(Collectors.toSet())).build(),
					Article.builder().title("hi").userGroups(Stream.of(UserGroups.A_GROUP, UserGroups.C_GROUP).collect(Collectors.toSet())).build(),
					Article.builder().title("hi").userGroups(Stream.of(UserGroups.B_GROUP, UserGroups.C_GROUP).collect(Collectors.toSet())).build(),
					Article.builder().title("hi").userGroups(Stream.of(UserGroups.A_GROUP, UserGroups.B_GROUP, UserGroups.C_GROUP).collect(Collectors.toSet())).build()).collect(Collectors.toList());

			articleRepository.saveAll(articleList);
		};
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
