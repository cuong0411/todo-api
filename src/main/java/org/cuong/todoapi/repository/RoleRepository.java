package org.cuong.todoapi.repository;

import java.util.Optional;

import org.cuong.todoapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String role);
}
