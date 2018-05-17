package com.naver.wheejuni.controller;

import com.naver.wheejuni.domain.Notification;
import com.naver.wheejuni.domain.UserNotificationInbox;
import com.naver.wheejuni.domain.repositories.mongo.UserNotificationInboxRepository;
import com.naver.wheejuni.security.tokens.PostAuthorizeToken;
import com.naver.wheejuni.service.specification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private NotificationService service;

    @Autowired
    private UserNotificationInboxRepository repository;


    @GetMapping(value = "/testevent/{id}", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<UserNotificationInbox> getEvents(@PathVariable long id) {

        Flux<UserNotificationInbox> stringFlux = Flux.fromStream(Stream.generate(() -> repository.findById(id)));
        Flux<Long> time = Flux.interval(Duration.ofSeconds(1L));

        return Flux.zip(stringFlux, time).map(Tuple2::getT1);
    }

    @GetMapping("/staticevent/{id}")
    public UserNotificationInbox getStaticInbox(@PathVariable long id) {
        return repository.findById(id);
    }

    @PostMapping("/flush/{id}")
    public void flushNotifications(@PathVariable long id, @RequestBody List<String> notificationIds) {
        service.setNotificationsRead(id, notificationIds);
    }
}
