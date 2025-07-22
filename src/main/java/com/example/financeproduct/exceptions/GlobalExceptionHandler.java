package com.example.financeproduct.exceptions;

import com.example.financeproduct.payload.ApiResponse;
import com.example.financeproduct.payload.ErrorData;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;
import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public HttpEntity<ApiResponse<ErrorData>> handle(RestException ex) {
        return ResponseEntity.status(400).body(ApiResponse.fail(ex.getMessage()));
    }

    @ExceptionHandler
    public HttpEntity<ApiResponse<ErrorData>> handle(EntityNotFoundException ex) {
        return ResponseEntity.status(400).body(ApiResponse.fail(ex.getMessage()));
    }

    @ExceptionHandler
    public HttpEntity<ApiResponse<ErrorData>> handle(MethodArgumentNotValidException ex) {
        List<FieldError> fieldErrors = ex.getFieldErrors();
        List<ErrorData> errorDataList = fieldErrors.stream()
                .map(fieldError -> ErrorData.error(fieldError.getField(), fieldError.getDefaultMessage()))
                .toList();

        return ResponseEntity.status(400).body(ApiResponse.fail(errorDataList));
    }

    @ExceptionHandler
    public HttpEntity<ApiResponse<ErrorData>> handle(IllegalArgumentException ex) {
        return ResponseEntity.status(500).body(ApiResponse.fail(ex.getMessage()));
    }

    @ExceptionHandler
    public HttpEntity<ApiResponse<ErrorData>> handle(AuthenticationException ex) {
        return ResponseEntity.status(401).body(ApiResponse.fail("Unauthorized: Please log in"));
    }

    @ExceptionHandler
    public HttpEntity<ApiResponse<ErrorData>> handle(AccessDeniedException ex) {
        return ResponseEntity.status(403).body(ApiResponse.fail("Forbidden: You don’t have access"));
    }
}
