package org.cuong.todoapi.exception;

public class TodoNotFoundById extends RuntimeException {
    public TodoNotFoundById(String message) {
        super(message);
    }
}
