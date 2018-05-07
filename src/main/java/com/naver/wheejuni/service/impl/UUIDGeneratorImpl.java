package com.naver.wheejuni.service.impl;

import com.naver.wheejuni.service.specification.UUIDGenerator;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UUIDGeneratorImpl implements UUIDGenerator {

    @Override
    public long generateUUID() {
        return UUID.randomUUID().getMostSignificantBits();
    }
}
