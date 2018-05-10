package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import com.google.common.collect.Sets;
import lombok.Getter;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Set;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserGroups {

    A_GROUP("A", "공지사항 등록만 해도 대박터지는 A그룹"),
    B_GROUP("B", "백종원이 직접 레시피를 전달해드려요! 식품유통업 사장님들을 위한 B그룹"),
    C_GROUP("C", "리누스 토발즈도 울고갈 신박한 소프트웨어들! IT/소프트웨어 분야 사장님들을 위한 C그룹");


    private String symbol;
    private String description;

    UserGroups(String symbol, String description) {
        this.symbol = symbol;
        this.description = description;
    }

    private boolean isCorrectName(String name) {
        return name.equalsIgnoreCase(this.name());
    }

    public String getType() {
        return this.name();
    }

    public static Set<UserGroups> findMatchingGroups(Set<String> queries) {
        Set<UserGroups> resultSet = Sets.newHashSet();

        queries.stream().forEach(q -> resultSet.add(Arrays.stream(UserGroups.values()).filter(g -> g.isCorrectName(q)).findFirst().orElseThrow(() -> new NoSuchElementException("올바른 그룹명이 아닙니다."))));
        return resultSet;
    }
}
