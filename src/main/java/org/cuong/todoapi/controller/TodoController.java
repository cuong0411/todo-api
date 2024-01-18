package org.cuong.todoapi.controller;

import org.cuong.todoapi.dto.TodoDto;
import org.cuong.todoapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto saveTodoDto = todoService.addTodo(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveTodoDto);
    }

    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") long id) {
        TodoDto foundTodo = todoService.getTodoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundTodo);
    }

    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        var todos = todoService.getAllTodos();
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id) {
        TodoDto updatedTodo = todoService.updateTodo(todoDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable("id") Long id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Todo with id: " + id + " was removed successfully");
    }

    @PatchMapping({"{id}/complete"})
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id) {
        TodoDto updatedTodo = todoService.completeTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }

    @PatchMapping({"{id}/in-complete"})
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id) {
        TodoDto updatedTodo = todoService.inCompleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }
}
