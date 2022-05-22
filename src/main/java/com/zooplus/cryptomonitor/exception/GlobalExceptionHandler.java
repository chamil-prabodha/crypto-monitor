package com.zooplus.cryptomonitor.exception;

import com.zooplus.cryptomonitor.model.response.APIResponse;
import com.zooplus.cryptomonitor.model.response.ErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {RestAPIException.class})
    protected ResponseEntity<Object> handleCryptoApiException(RestAPIException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("400");
        errorResponse.setMessage(e.getMessage());
        APIResponse<Object> apiResponse = new APIResponse<>(false, null, errorResponse);
        return handleExceptionInternal(e, apiResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = {CryptoAPIException.class})
    protected ResponseEntity<Object> handleCryptoApiException(CryptoAPIException e, WebRequest request) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setCode("400");
        errorResponse.setMessage(e.getMessage());
        errorResponse.setAdditionalInfo(e.getAdditionalInfo());
        APIResponse<Object> apiResponse = new APIResponse<>(false, e.getDefaultResponse(), errorResponse);
        return handleExceptionInternal(e, apiResponse, new HttpHeaders(), e.getStatusCode(), request);
    }
}
