package com.vmware.num_gen.imp.util.handler;


import com.vmware.num_gen.imp.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerRequestExceptionHandler {
    private static Logger LOGGER = LoggerFactory.getLogger(ControllerRequestExceptionHandler.class);

    public ControllerRequestExceptionHandler() {
    }

    @ExceptionHandler({Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity unhandledException(Exception ex) {
        String message = "Something went wrong";
        return new ResponseEntity(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler({BusinessException.class})
    public ResponseEntity businessExceptionHandler(BusinessException ex) {
        String message = ex.getErrorCode();
        return new ResponseEntity(message, ex.getHttpStatus() == null ? HttpStatus.MULTIPLE_CHOICES : ex.getHttpStatus());
    }
}