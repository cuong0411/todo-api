package org.cuong.todoapi.repository;

import org.cuong.todoapi.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
