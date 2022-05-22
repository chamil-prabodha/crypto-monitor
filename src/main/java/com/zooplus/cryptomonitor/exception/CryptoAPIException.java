package com.zooplus.cryptomonitor.exception;

import org.springframework.http.HttpStatus;

public class CryptoAPIException extends RestAPIException {
    private final Object defaultResponse;

    public <T> CryptoAPIException(String message, HttpStatus statusCode, String additionalInfo, T defaultResponse) {
        super(message, statusCode, additionalInfo);
        this.defaultResponse = defaultResponse;
    }

    public Object getDefaultResponse() {
        return defaultResponse;
    }
}
