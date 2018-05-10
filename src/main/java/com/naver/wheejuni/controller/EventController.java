package com.naver.wheejuni.controller;

import com.naver.wheejuni.domain.UserNotificationInbox;
import com.naver.wheejuni.service.specification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private NotificationService service;


    @GetMapping(value = "/testevent", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<String> getEvents() {
        Flux<String> stringFlux = Flux.fromStream(Stream.generate(() -> "fuck!\n"));
        Flux<Long> time = Flux.interval(Duration.ofSeconds(1L));

        return Flux.zip(stringFlux, time).map(Tuple2::getT1);
    }

}
