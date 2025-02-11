package ru.springdatajpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springdatajpa.springboot.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
