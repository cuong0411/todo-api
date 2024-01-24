package org.cuong.todoapi.controller;

import org.cuong.todoapi.exception.RoleNotFoundByName;
import org.cuong.todoapi.exception.TodoApiException;
import org.cuong.todoapi.exception.TodoNotFoundById;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(TodoNotFoundById.class)
    public ResponseEntity<String> todoNotFound(TodoNotFoundById exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(RoleNotFoundByName.class)
    public ResponseEntity<String> roleNotFound(RoleNotFoundByName exception) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }

    @ExceptionHandler(TodoApiException.class)
    public ResponseEntity<String> todoApiException(TodoApiException exception) {
        return ResponseEntity.status(exception.getHttpStatus()).body(exception.getMessage());
    }
}
