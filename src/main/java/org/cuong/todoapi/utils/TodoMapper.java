package org.cuong.todoapi.utils;

import org.cuong.todoapi.dto.TodoDto;
import org.cuong.todoapi.entity.Todo;
import org.springframework.stereotype.Component;

@Component
public class TodoMapper {

    public Todo convertToTodo(TodoDto todoDto) {
        return new Todo(
                todoDto.getId(),
                todoDto.getTitle(),
                todoDto.getDescription(),
                todoDto.isCompleted()
        );
    }

    public TodoDto convertToTodoDto(Todo Todo) {
        return new TodoDto(
                Todo.getId(),
                Todo.getTitle(),
                Todo.getDescription(),
                Todo.isCompleted()
        );
    }
}
