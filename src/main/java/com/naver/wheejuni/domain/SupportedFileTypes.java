package com.naver.wheejuni.domain;

import lombok.Getter;
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.Arrays;

@Getter
public enum SupportedFileTypes {

    XLS("xls", "application/vnd.ms-excel"),
    XLSX("xlsx", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"),
    CSV("csv", "text/csv"),
    TSV("tsv", "text/tsv"),
    TXT("txt", "text/plain"),
    PNG("png", "image/png");

    private String extension;
    private String mimeType;

    SupportedFileTypes(String extension, String mimeType) {
        this.extension = extension;
        this.mimeType = mimeType;
    }

    private boolean isMatchingType(String extension) {
        return extension.equalsIgnoreCase(this.extension);
    }

    public static boolean isSupportedFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        return Arrays.stream(SupportedFileTypes.values()).anyMatch(t -> t.isMatchingType(FilenameUtils.getExtension(filename)));
    }
}
