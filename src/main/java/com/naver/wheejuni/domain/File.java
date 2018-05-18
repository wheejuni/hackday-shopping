package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.naver.wheejuni.domain.validation.annotation.EligibleFileTypes;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@NoArgsConstructor
@Embeddable
public class File {

    private String path;
    private String storeFilePath;

    public File(String path, String storeFilePath) {
        this.path = path;
        this.storeFilePath = storeFilePath;
    }
}
