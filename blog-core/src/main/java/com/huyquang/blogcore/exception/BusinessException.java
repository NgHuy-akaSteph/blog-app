package com.huyquang.blogcore.exception;

public class BusinessException extends AppException{
    public BusinessException(ErrorCode errorCode, Object... args) {
        super(errorCode, args);
    }
}
