package com.huyquang.blogcore.exception;

import com.huyquang.blogcore.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        String errorMessage = ex.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return ResponseEntity
                .badRequest()
                .body(ApiResponse.error(errorMessage));
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error("Internal Error: " + ex.getMessage()));
    }
}
