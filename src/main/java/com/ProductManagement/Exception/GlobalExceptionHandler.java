package com.ProductManagement.Exception;

import java.util.Map;
import java.util.stream.Collectors;

import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Map<String,String>> handleNotFound(ResourceNotFoundException ex) {
    Map<String,String> body = Map.of("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
  }

  @ExceptionHandler(BadRequestException.class)
  public ResponseEntity<Map<String,String>> handleBadRequest(BadRequestException ex) {
    Map<String,String> body = Map.of("error", ex.getMessage());
    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
  }

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String,String>> validationErrors(MethodArgumentNotValidException ex) {
    Map<String,String> errors = ex.getBindingResult().getFieldErrors().stream()
      .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
    return ResponseEntity.badRequest().body(errors);
  }
  
}


