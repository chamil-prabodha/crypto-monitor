package com.zooplus.cryptomonitor.exception;

import org.springframework.http.HttpStatus;

public abstract class RestAPIException extends Exception {
    private final HttpStatus statusCode;
    private final String additionalInfo;

    public RestAPIException(String message, HttpStatus statusCode, String additionalInfo) {
        super(message);
        this.statusCode = statusCode;
        this.additionalInfo = additionalInfo;
    }

    public HttpStatus getStatusCode() {
        return statusCode;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

}
