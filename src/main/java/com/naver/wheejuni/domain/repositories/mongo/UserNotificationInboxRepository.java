package com.naver.wheejuni.domain.repositories.mongo;

import com.naver.wheejuni.domain.UserNotificationInbox;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface UserNotificationInboxRepository extends ReactiveMongoRepository<UserNotificationInbox, Long> {

}
