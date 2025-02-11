package ru.springdatajpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springdatajpa.springboot.entity.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
