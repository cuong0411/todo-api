package org.cuong.todoapi.service;

import org.cuong.todoapi.dto.TodoDto;
import org.cuong.todoapi.entity.Todo;
import org.cuong.todoapi.exception.TodoNotFoundById;
import org.cuong.todoapi.repository.TodoRepository;
import org.cuong.todoapi.utils.TodoMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService{

    private final TodoRepository todoRepository;
    private final TodoMapper todoMapper;

    public TodoServiceImpl(TodoRepository todoRepository, TodoMapper todoMapper) {
        this.todoRepository = todoRepository;
        this.todoMapper = todoMapper;
    }

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = todoMapper.convertToTodo(todoDto);
        Todo savedTodo = todoRepository.save(todo);
        return todoMapper.convertToTodoDto(savedTodo);
    }

    @Override
    public TodoDto getTodoById(Long id) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(() -> new TodoNotFoundById("Todo with id: " + id + " Not found."));
        return todoMapper.convertToTodoDto(todo);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        return todoRepository.findAll()
                .stream()
                .map(todoMapper::convertToTodoDto)
                .collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto, Long id) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(() -> new TodoNotFoundById("Todo with id: " + id + " Not found."));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.convertToTodoDto(updatedTodo);
    }

    @Override
    public void deleteTodoById(Long id) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(() -> new TodoNotFoundById("Todo with id: " + id + " Not found."));
        todoRepository.deleteById(todo.getId());
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(() -> new TodoNotFoundById("Todo with id: " + id + " Not found."));
        todo.setCompleted(Boolean.TRUE);
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.convertToTodoDto(updatedTodo);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository
                .findById(id)
                .orElseThrow(() -> new TodoNotFoundById("Todo with id: " + id + " Not found."));
        todo.setCompleted(Boolean.FALSE);
        Todo updatedTodo = todoRepository.save(todo);
        return todoMapper.convertToTodoDto(updatedTodo);
    }
}
