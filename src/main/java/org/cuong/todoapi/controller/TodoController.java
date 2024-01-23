package org.cuong.todoapi.controller;

import org.cuong.todoapi.dto.TodoDto;
import org.cuong.todoapi.service.TodoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    private final TodoService todoService;

    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<TodoDto> addTodo(@RequestBody TodoDto todoDto) {
        TodoDto saveTodoDto = todoService.addTodo(todoDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saveTodoDto);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping("{id}")
    public ResponseEntity<TodoDto> getTodoById(@PathVariable("id") long id) {
        TodoDto foundTodo = todoService.getTodoById(id);
        return ResponseEntity.status(HttpStatus.OK).body(foundTodo);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @GetMapping
    public ResponseEntity<List<TodoDto>> getAllTodos() {
        var todos = todoService.getAllTodos();
        return ResponseEntity.status(HttpStatus.OK).body(todos);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto, @PathVariable("id") Long id) {
        TodoDto updatedTodo = todoService.updateTodo(todoDto, id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTodoById(@PathVariable("id") Long id) {
        todoService.deleteTodoById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Todo with id: " + id + " was removed successfully");
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PatchMapping({"{id}/complete"})
    public ResponseEntity<TodoDto> completeTodo(@PathVariable("id") Long id) {
        TodoDto updatedTodo = todoService.completeTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }

    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    @PatchMapping({"{id}/in-complete"})
    public ResponseEntity<TodoDto> inCompleteTodo(@PathVariable("id") Long id) {
        TodoDto updatedTodo = todoService.inCompleteTodo(id);
        return ResponseEntity.status(HttpStatus.OK).body(updatedTodo);
    }
}
