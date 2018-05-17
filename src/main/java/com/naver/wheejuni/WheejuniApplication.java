package com.naver.wheejuni;

import com.google.common.collect.Sets;
import com.naver.wheejuni.domain.*;
import com.naver.wheejuni.domain.repositories.jpa.AccountRepository;
import com.naver.wheejuni.domain.repositories.jpa.ArticleRepository;
import com.naver.wheejuni.domain.repositories.mongo.UserNotificationInboxRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "com.naver.wheejuni.domain.repositories.jpa")
@EnableMongoRepositories(basePackages = "com.naver.wheejuni.domain.repositories.mongo")
public class WheejuniApplication {

	public static final String APPLICATION_LOCATIONS = "spring.config.location="
			+ "classpath:application.yml,"
			+ "/source/config/application-prod.yml";

	public static void main(String[] args) {
		new SpringApplicationBuilder(WheejuniApplication.class)
				.properties(APPLICATION_LOCATIONS)
				.run(args);
	}

	@Bean
	CommandLineRunner getData(UserNotificationInboxRepository inboxRepository) {
		return args -> {

			UserNotificationInbox inbox = new UserNotificationInbox();

			inbox.setId(1L);
			inbox.setListeningGroups(Arrays.stream(UserGroups.values()).collect(Collectors.toList()));

			inboxRepository.deleteAll();
			inboxRepository.save(inbox);

		};
	}

	@Bean
	CommandLineRunner bootStrapAccountsandArticles(AccountRepository repository, PasswordEncoder passwordEncoder, ArticleRepository articleRepository) {
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

			List<Article> articleList= Stream.of(Article.builder().title("hi").userGroups(UserGroups.C_GROUP).build(),
					Article.builder().title("hi").userGroups(UserGroups.B_GROUP).build(),
					Article.builder().title("test article").userGroups(UserGroups.A_GROUP).build()).collect(Collectors.toList());

			articleRepository.saveAll(articleList);
		};
	}

	@Bean
	public MultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();
	}

}
