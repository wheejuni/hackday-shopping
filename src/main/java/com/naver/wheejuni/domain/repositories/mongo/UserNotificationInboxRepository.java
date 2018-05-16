package com.naver.wheejuni.domain.repositories.mongo;

import com.naver.wheejuni.domain.UserGroups;
import com.naver.wheejuni.domain.UserNotificationInbox;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.Set;

public interface UserNotificationInboxRepository extends MongoRepository<UserNotificationInbox, Long> {

    UserNotificationInbox findById(long id);

    @Query(value = "{listeningGroups: {$in: ?0}}")
    List<UserNotificationInbox> findByMatchingGroups(Set<UserGroups> groups);
}
