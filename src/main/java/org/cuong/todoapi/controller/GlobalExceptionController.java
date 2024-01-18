package org.cuong.todoapi.controller;

import org.cuong.todoapi.exception.TodoNotFoundById;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(TodoNotFoundById.class)
    public ResponseEntity<String> deleteCatById(TodoNotFoundById exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
    }
}
