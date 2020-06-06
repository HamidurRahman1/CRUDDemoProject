package com.hamidur.gainam.exceptions;

import com.hamidur.gainam.exceptions.custom.CustomerNotFound;
import com.hamidur.gainam.exceptions.custom.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@ControllerAdvice
public class AppExceptionHandler
{
    @ResponseBody
    @ExceptionHandler(value = CustomerNotFound.class)
    public ResponseEntity<ErrorResponse> notFound(CustomerNotFound customerNotFound)
    {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), customerNotFound.getErrorMessage(),
                HttpStatus.NOT_FOUND.value()), HttpStatus.NOT_FOUND);
    }
}
