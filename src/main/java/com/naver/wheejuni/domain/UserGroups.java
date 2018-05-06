package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum UserGroups {

    A_GROUP("A"),
    B_GROUP("B"),
    C_GROUP("C");

    @JsonValue
    private String symbol;

    UserGroups(String symbol) {
        this.symbol = symbol;
    }

    public String getType() {
        return this.name();
    }

}
