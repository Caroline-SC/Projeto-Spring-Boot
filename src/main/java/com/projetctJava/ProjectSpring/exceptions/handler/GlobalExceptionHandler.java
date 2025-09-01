package com.projetctJava.ProjectSpring.exceptions.handler;

import com.projetctJava.ProjectSpring.exceptions.custom.*;
import com.projetctJava.ProjectSpring.exceptions.model.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler({ResourceNotFoundException.class,NoResourceFoundException.class})
    public ResponseEntity<ErrorResponse> resourceNotFound(Exception e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        ErrorResponse err = new ErrorResponse(
                Instant.now(),
                status.value(),
                "Resource not found",
                e.getMessage(),
                request.getRequestURI() );

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(IllegalStatusException.class)
    public ResponseEntity<ErrorResponse> illegalStatusException(IllegalStatusException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.UNPROCESSABLE_ENTITY;
        ErrorResponse err = new ErrorResponse(
                Instant.now(),
                status.value(),
                "Validation error",
                e.getMessage(),
                request.getRequestURI() );

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler({DateInvalidFormatterException.class, NumberFormatException.class,InvalidParamException.class})
    public ResponseEntity<ErrorResponse> DateTimeFormatterException(Exception e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse err = new ErrorResponse(
                Instant.now(),
                status.value(),
                "Invalid value",
                e.getMessage(),
                request.getRequestURI() );

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<ErrorResponse> MissingServletRequestParameterException(
            MissingServletRequestParameterException e, HttpServletRequest request){

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse err = new ErrorResponse(
                Instant.now(),
                status.value(),
                String.format("Missing mandatory parameter: '%s' (type: %s)",
                        e.getParameterName(),
                        e.getParameterType()),
                e.getMessage(),
                request.getRequestURI() );

        return ResponseEntity.status(status).body(err);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(
            MethodArgumentNotValidException e, HttpServletRequest request) {

        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        HttpStatus status = HttpStatus.BAD_REQUEST;
        ErrorResponse err = new ErrorResponse(
                Instant.now(),
                status.value(),
                errors.entrySet().stream()
                        .map(entry -> entry.getKey() + ": " + entry.getValue())
                        .collect(Collectors.joining(", ")),
                "Invalid input data",
                request.getRequestURI());

        return ResponseEntity.status(status).body(err);

    }
    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateException(DuplicateResourceException e,HttpServletRequest request) {
        HttpStatus status = HttpStatus.CONFLICT;
        ErrorResponse err = new ErrorResponse(
                Instant.now(),
                status.value(),
                String.format("There is already a feature with this %s.",e.getAttribute().toLowerCase()),
                e.getMessage(),
                request.getRequestURI() );
        return ResponseEntity.status(status).body(err);
    }
}
