package ru.springdatajpa.springboot.repository;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Address;
import ru.springdatajpa.springboot.entity.Order;
import ru.springdatajpa.springboot.entity.OrderItem;

import java.math.BigDecimal;

@SpringBootTest
public class OneToManyMappingTest {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    // save order along with also save it's order items
    @Test
    void saveOrderMethod(){
        Order order = new Order();
        order.setOrderTrackingNumber("100ABC");
        order.setStatus("In progress");

        // create order item 1
        OrderItem orderItem1 = new OrderItem();
        orderItem1.setProduct(productRepository.findById(1L).get());
        orderItem1.setQuantity(2);
        orderItem1.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(2)));
        orderItem1.setImageUrl("image1.png");
        order.getOrderItems().add(orderItem1);

        // create order item 2
        OrderItem orderItem2 = new OrderItem();
        orderItem2.setProduct(productRepository.findById(2L).get());
        orderItem2.setQuantity(2);
        orderItem2.setPrice(orderItem1.getProduct().getPrice().multiply(new BigDecimal(3)));
        orderItem2.setImageUrl("image2.png");
        order.getOrderItems().add(orderItem2);

        order.setTotalPrice(order.getTotalAmount());
        order.setTotalQuantity(2);

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
    void fetchOrderMethod(){
        Order order = orderRepository.findById(1L).get();
        System.out.println(order);
        System.out.println("=========");
        order.getOrderItems().forEach(item-> System.out.println(item.getProduct().getName() + "\n"));
    }

    @Test
    void deleteOrderMethod(){
        orderRepository.deleteById(1L);
    }
}
