package com.naver.wheejuni.exceptions.file;

public class FileProcessingException extends RuntimeException {

    public FileProcessingException(String message, Throwable t) {
        super(message, t);
    }
}
