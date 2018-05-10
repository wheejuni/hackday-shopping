package com.naver.wheejuni.domain.converter;

import com.naver.wheejuni.domain.File;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class FileConverter implements AttributeConverter<File, String> {

    @Override
    public String convertToDatabaseColumn(File file) {
        if(file == null) {
            return "no file";
        }
        return file.getPath();
    }

    @Override
    public File convertToEntityAttribute(String s) {
        return new File(s);
    }
}
