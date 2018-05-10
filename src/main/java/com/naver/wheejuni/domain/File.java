package com.naver.wheejuni.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.naver.wheejuni.domain.validation.annotation.EligibleFileTypes;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class File implements Serializable {

    @EligibleFileTypes
    private String path;

}
