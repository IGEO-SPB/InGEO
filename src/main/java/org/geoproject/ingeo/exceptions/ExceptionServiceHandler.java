package com.geoproject.igeo.exceptions;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionServiceHandler {

    @ExceptionHandler(value = NoContainerNumberEnteredException.class)
    private String handleNotFoundException(NotFoundException exception) {
        System.out.println("TEST EXCEPTION HANDLER - NoContainerNumberEnteredException");
        return exception.getMessage();
    }

    @ExceptionHandler(value = ZeroDivisionException.class)
    private String handleZeroDivisionException(ZeroDivisionException exception) {
        System.out.println("TEST EXCEPTION HANDLER - ZeroDivisionException");
        return exception.getMessage();
    }
}
