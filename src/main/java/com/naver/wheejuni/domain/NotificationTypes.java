package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum NotificationTypes {

    CREATE("새 공지사항이 등록되었습니다: "),
    MODIFY("공지사항이 수정되었습니다: ");

    private String caption;

    NotificationTypes(String caption) {
        this.caption = caption;
    }

    public String getType() {
        return this.name();
    }
}
