package ru.springdatajpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springdatajpa.springboot.entity.OnlineOrder;

public interface OnlineOrderRepository extends JpaRepository<OnlineOrder, Long> {

}
