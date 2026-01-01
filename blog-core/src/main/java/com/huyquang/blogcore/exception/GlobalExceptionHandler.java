package com.huyquang.blogcore.exception;

import com.huyquang.blogcore.dto.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<ApiResponse<?>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        // Collect field errors
        ex.getBindingResult().getFieldErrors().forEach(
                error -> errors.put(error.getField(), error.getDefaultMessage())
        );

        // Use VALIDATION_FAILED error code
        ErrorCode errorCode = ErrorCode.VALIDATION_FAILED;
        ApiResponse<Map<String, String>> response = ApiResponse.<Map<String, String>>builder()
                .status(errorCode.getCode())
                .message(errorCode.getMessage())
                .data(errors)
                .build();

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(BusinessException.class)
    ResponseEntity<ApiResponse<?>> handleBusinessException(BusinessException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        String message = errorCode.getMessage();

        log.warn("Business Exception: [{}] {}", errorCode.getCode(), message);

        // 1. Format message with args if any
        if (ex.getArgs() != null && ex.getArgs().length > 0) {
            message = MessageFormat.format(errorCode.getMessage(), ex.getArgs());
        }

        // 2. Build response entity
        ApiResponse<Object> response = ApiResponse.error(errorCode.getCode(), message);

        return ResponseEntity
                .badRequest()
                .body(response);
    }

    @ExceptionHandler(AppException.class)
    ResponseEntity<ApiResponse<?>> handleAppException(AppException ex) {
        ErrorCode errorCode = ex.getErrorCode();
        String message = errorCode.getMessage();

        log.error("App Exception: [{}] {}", errorCode.getCode(), message, ex);

        // 1. Format message with args if any
        if (ex.getArgs() != null && ex.getArgs().length > 0) {
            message = MessageFormat.format(errorCode.getMessage(), ex.getArgs());
        }

        // 2. Build response entity
        ApiResponse<Object> response = ApiResponse.error(errorCode.getCode(), message);

        return ResponseEntity
                .status(errorCode.getStatusCode())
                .body(response);
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<ApiResponse<?>> handleGenericException(Exception ex) {
        log.error("Unexpected error occurred", ex);
        return ResponseEntity
                .internalServerError()
                .body(ApiResponse.error(500, "An unexpected error occurred"));
    }
}
