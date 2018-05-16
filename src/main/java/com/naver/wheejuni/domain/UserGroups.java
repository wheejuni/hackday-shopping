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

    A_GROUP("A그룹", "공지사항 등록만 해도 대박터지는 A그룹"),
    B_GROUP("B그룹", "공지사항 읽기만해도 클릭이 쏟아지는 B그룹"),
    C_GROUP("C그룹", "공지사항 수정 안해도 처음부터 잘 써지는 C그룹");


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

    public static Set<UserGroups> findMatchingGroupSingle(String query) {
        Set<UserGroups> returnGroup = Sets.newHashSet();
        Arrays.stream(UserGroups.values()).filter(g -> g.isCorrectName(query)).forEach(returnGroup::add);

        return returnGroup;
    }

    public static Set<UserGroups> findMatchingGroups(Set<String> queries) {
        Set<UserGroups> resultSet = Sets.newHashSet();

        queries.stream().forEach(q -> resultSet.add(Arrays.stream(UserGroups.values()).filter(g -> g.isCorrectName(q)).findFirst().orElseThrow(() -> new NoSuchElementException("올바른 그룹명이 아닙니다."))));
        return resultSet;
    }
}
