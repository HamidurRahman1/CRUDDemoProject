package com.hamidur.gainam.exceptions;

import com.hamidur.gainam.exceptions.custom.CustomerNotFound;
import com.hamidur.gainam.exceptions.custom.ErrorResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolationException;
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

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> missMatchType(MethodArgumentTypeMismatchException missType)
    {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), missType.getLocalizedMessage().split(";")[0],
                HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity<ErrorResponse> violation(ConstraintViolationException v)
    {
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), v.getLocalizedMessage(),
                HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }

    @ResponseBody
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> violation(MethodArgumentNotValidException v)
    {
        String[] s = v.getLocalizedMessage().split(";");
        return new ResponseEntity<>(new ErrorResponse(LocalDateTime.now(), s[s.length-1],
                HttpStatus.BAD_REQUEST.value()), HttpStatus.BAD_REQUEST);
    }
}
