package ru.springdatajpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.springdatajpa.springboot.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByOrderTrackingNumber(String orderTrackingNumber);

    @Override
    @Transactional(timeout = 10)
    void deleteById(Long aLong);

    @Override
    @Transactional(propagation = Propagation.NEVER)
    <S extends Order> S save(S entity);
}
