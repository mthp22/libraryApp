package com.lm.backend.common;

public class BusinessException extends RuntimeException {
    public BusinessException(String message) {
        super(message);
    }
}
