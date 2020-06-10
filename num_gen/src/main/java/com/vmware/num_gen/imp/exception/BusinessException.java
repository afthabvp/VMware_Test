package com.vmware.num_gen.imp.exception;

import org.springframework.http.HttpStatus;

public class BusinessException extends RuntimeException {
    private String errorCode;
    private HttpStatus httpStatus;

    public String getErrorCode() {
        return this.errorCode;
    }

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }

    public static BusinessException throwBusinessException(HttpStatus httpStatus, String errorCode) throws BusinessException {
        throw new BusinessException(errorCode, (Throwable)null, httpStatus);
    }

    public static BusinessException throwBusinessException(String errorCode) throws BusinessException {
        throw new BusinessException(errorCode, (Throwable)null, (HttpStatus)null);
    }


    public BusinessException(String errorCode, Throwable cause, HttpStatus httpStatus) {
        super(errorCode, cause == null ? null : cause);
        this.httpStatus = httpStatus;
        this.errorCode = errorCode;
    }
}