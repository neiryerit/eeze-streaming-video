package com.eeze.streaming.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import com.eeze.streaming.domain.Error;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolationException;
/*
 * Class aimed for controling all exceptions and give a customized response to the consumer
 * while the whole stack error is wrote in the execution console of the project.
 * All application errors will be caught here
 */
@RestControllerAdvice
public class ErrorHandler {

    private Logger log = LogManager.getLogger(ErrorHandler.class);

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationExceptions(MethodArgumentNotValidException ex) {

        log.error("CLIENT ERROR: {}", ex.getMessage());

        List<String> messages = ex.getBindingResult().getFieldErrors().stream()
                .map(err -> err.getField() + ": " + err.getDefaultMessage())
                .collect(Collectors.toList());
        log.error(messages.toArray());
        Error error = new Error();
        error.setMessages(messages);

        return ResponseEntity.badRequest().body(messages);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handleConstraintViolationExceptions(ConstraintViolationException ex) {

        log.error("CLIENT ERROR: {}", ex.getMessage());

        List<String> messages = ex.getConstraintViolations().stream()
                .map(err -> err.getPropertyPath().toString() + ": " + err.getMessage())
                .collect(Collectors.toList());

        return ResponseEntity.badRequest().body(messages);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        log.error("CLIENT ERROR: {}", ex.getMessage());

        return ResponseEntity.badRequest().body(getSingleMsg("Malformed JSON request: " + ex.getMessage()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<?> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {

        log.error("CLIENT ERROR: {}", ex.getMessage());

        return ResponseEntity.badRequest().body(
                getSingleMsg(String.format("Missing required parameter: %s", ex.getParameterName())));
    }

    @ExceptionHandler(TypeMismatchException.class)
    public ResponseEntity<?> handleTypeMismatchException(TypeMismatchException ex) {

        log.error("CLIENT ERROR: {}", ex.getMessage());

        return ResponseEntity.badRequest().body(
                getSingleMsg(
                        String.format("Invalid value for parameter '%s': %s", ex.getPropertyName(), ex.getValue())));
    }

    // Generic handler for any other exceptions leading to HTTP 400
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException ex) {

        log.error("CLIENT ERROR: {}", ex.getMessage());
        
        return ResponseEntity.badRequest().body(getSingleMsg("Invalid request: " + ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleAllErrors(HttpServletRequest req, Exception ex) {

        log.error("UNKNOWN ERROR is: {}", ex.getMessage());

        Arrays.stream(ex.getStackTrace()).limit(10).forEach(x -> {
            log.error("class:{} - method:{} - line:{}", x.getClassName(), x.getMethodName(), x.getLineNumber());
        });

        return ResponseEntity.internalServerError().body(getSingleMsg("Something bad happend, please try later"));
    }

    public static Error getSingleMsg(String text) {

        Error error = new Error();
        List<String> messages = new ArrayList<>();
        messages.add(text);
        error.setMessages(messages);
        return error;
    }
}
