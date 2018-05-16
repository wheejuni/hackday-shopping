package com.naver.wheejuni;

import com.google.common.collect.Sets;
import com.naver.wheejuni.domain.*;
import com.naver.wheejuni.domain.repositories.jpa.AccountRepository;
import com.naver.wheejuni.domain.repositories.jpa.ArticleRepository;
import com.naver.wheejuni.domain.repositories.mongo.UserNotificationInboxRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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

	public static void main(String[] args) {
		SpringApplication.run(WheejuniApplication.class, args);
	}

	@Bean
	CommandLineRunner getData(ArticleRepository articleRepository, UserNotificationInboxRepository inboxRepository) {
		return args -> {
			Set<UserGroups> targetGroups = Sets.newHashSet();
			Arrays.stream(UserGroups.values()).forEach(targetGroups::add);

			articleRepository.save(Article.builder().title("hey").userGroups(targetGroups).content("hi").build());


			UserNotificationInbox inbox = new UserNotificationInbox();

			inbox.setId(1L);
			inbox.setListeningGroups(Arrays.stream(UserGroups.values()).collect(Collectors.toList()));

			inboxRepository.deleteAll();
			inboxRepository.save(inbox);

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
