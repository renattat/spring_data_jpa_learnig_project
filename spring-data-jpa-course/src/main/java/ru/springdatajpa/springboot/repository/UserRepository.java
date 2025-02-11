package ru.springdatajpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springdatajpa.springboot.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
}
