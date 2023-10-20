package com.ms.training.application.exception;

public class BusinessException extends RuntimeException {
    private final String errorCode;

    public BusinessException(String errorCode, String errorMessage) {
        super(errorMessage);
        this.errorCode = errorCode;
    }
    public String getErrorCode() {
        return errorCode;
    }

}
