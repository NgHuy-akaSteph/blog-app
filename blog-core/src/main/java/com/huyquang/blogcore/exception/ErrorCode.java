package com.huyquang.blogcore.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_EXISTED(1400, "User already existed", HttpStatus.BAD_REQUEST),
    VALIDATION_FAILED(1401, "Validation failed", HttpStatus.BAD_REQUEST),
    INTERNAL_ERROR(1500, "Internal server error", HttpStatus.INTERNAL_SERVER_ERROR)
    ;

    private final int code;
    private final String message;
    private final HttpStatusCode statusCode;
}
