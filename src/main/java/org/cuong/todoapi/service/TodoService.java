package org.cuong.todoapi.service;

import org.cuong.todoapi.dto.TodoDto;
import org.springframework.security.access.prepost.PreAuthorize;

import java.util.List;

public interface TodoService {

    @PreAuthorize("hasRole('ADMIN')")
    TodoDto addTodo(TodoDto todoDto);

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    TodoDto getTodoById(Long id);

    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    List<TodoDto> getAllTodos();

    @PreAuthorize("hasRole('ADMIN')")
    TodoDto updateTodo(TodoDto todoDto, Long id);

    @PreAuthorize("hasRole('ADMIN')")
    void deleteTodoById(Long id);

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    TodoDto completeTodo(Long id);

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    TodoDto inCompleteTodo(Long id);
}
