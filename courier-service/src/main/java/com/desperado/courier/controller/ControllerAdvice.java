package com.desperado.courier.controller;

import com.desperado.courier.dto.response.BadRequestResponse;
import com.desperado.courier.dto.response.NotFoundResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.webjars.NotFoundException;

@RestControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<?> handleResourceNotFound(NotFoundException e) {
        return ResponseEntity.status(404).body(new NotFoundResponse());
    }


    @ExceptionHandler({IllegalStateException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?>  handleIllegalState(IllegalStateException e) {
        return ResponseEntity.ofNullable(new BadRequestResponse());
    }


    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        return ResponseEntity.ofNullable(new BadRequestResponse());
    }

    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handleConstraintViolation(ConstraintViolationException e) {
        return ResponseEntity.ofNullable(new BadRequestResponse());
    }


}
