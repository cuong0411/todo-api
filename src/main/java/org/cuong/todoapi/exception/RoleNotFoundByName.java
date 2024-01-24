package org.cuong.todoapi.exception;

public class RoleNotFoundByName extends RuntimeException {
    public RoleNotFoundByName(String message) {
        super(message);
    }
}
