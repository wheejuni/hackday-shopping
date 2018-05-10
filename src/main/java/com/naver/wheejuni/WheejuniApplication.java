package com.naver.wheejuni;

import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoClients;
import com.naver.wheejuni.domain.Article;
import com.naver.wheejuni.domain.UserNotificationInbox;
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
import org.springframework.web.reactive.config.EnableWebFlux;
import reactor.core.publisher.Flux;

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

}
