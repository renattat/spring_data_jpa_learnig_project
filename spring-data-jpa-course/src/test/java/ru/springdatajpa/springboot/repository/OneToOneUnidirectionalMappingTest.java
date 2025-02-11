package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Address;
import ru.springdatajpa.springboot.entity.Order;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneUnidirectionalMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("1000ABC");
        order.setTotalQuantity(5);
        order.setStatus("IN PROGRESS");
        order.setTotalPrice(new BigDecimal(1000));

        Address address = new Address();
        address.setCity("MOSCOW");
        address.setStreet("NOVAYA");
        address.setState("MOSCOW REGION");
        address.setCountry("RUSSIA");
        address.setZipCode("611047");

        order.setBillingAddress(address);

        orderRepository.save(order);
    }

    @Test
    void updateOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        order.setStatus("DELIVERED");

        Address address = order.getBillingAddress();
        address.setZipCode("007001");

        orderRepository.save(order);
    }

    @Test
    void getOrderMethod() {
        Order order = orderRepository.findById(2L).get();

        System.out.println(order.toString());
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }


}
