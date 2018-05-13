package com.naver.wheejuni.exceptions.general;

public class InternalServerError extends RuntimeException {

    public InternalServerError(String msg) {
        super(msg);
    }

    public InternalServerError(String msg, Throwable t) {
        super(msg, t);
    }
}
