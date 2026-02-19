package com.gaitanalyze.gaitanalysisapp.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    //bad req handler for missing field
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex){
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    //Not found handler
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleResourceNotFound(ResourceNotFoundException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("error", "Not Found");
        errorRes.put("message", ex.getMessage());
        return new ResponseEntity<>(errorRes, HttpStatus.NOT_FOUND);
    }

    //duplicate data
    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, String>> handleDuplicateEntry(DataIntegrityViolationException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("error", "Conflict");
        errorRes.put("message", "Data already exist.");
        return new ResponseEntity<>(errorRes, HttpStatus.CONFLICT);
    }

    //Malformed json
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Map<String, String>> handleMalformedJson(HttpMessageNotReadableException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("error", "Bad Request");
        errorRes.put("message", "Malformed JSON request. Check your data types.");
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }

    //Wrong Http method
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Map<String, String>> handleMethodNotSupported(HttpRequestMethodNotSupportedException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("error", "Method Not Allowed");
        errorRes.put("message", "This endpoint does not support the used HTTP method.");
        return new ResponseEntity<>(errorRes, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //Type mismatch
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Map<String, String>> handleTypeMismatch(MethodArgumentTypeMismatchException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("error", "Bad Request");
        errorRes.put("message", "Invalid parameter type: " + ex.getName());
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }

    //Request body error
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> handleReqBodyEmpty(IllegalArgumentException ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("error", "Bad Request");
        errorRes.put("message", ex.getMessage());
        return new ResponseEntity<>(errorRes, HttpStatus.BAD_REQUEST);
    }

    //Generic fallback for uncatch error
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGenericException(Exception ex){
        Map<String, String> errorRes = new HashMap<>();
        errorRes.put("error", "Internal Server Error");
        errorRes.put("message", "An unexpected error occurred.");
        return new ResponseEntity<>(errorRes, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
