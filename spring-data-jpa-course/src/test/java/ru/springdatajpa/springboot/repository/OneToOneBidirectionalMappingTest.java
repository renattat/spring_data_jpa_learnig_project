package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Address;
import ru.springdatajpa.springboot.entity.Order;

import java.math.BigDecimal;

@SpringBootTest
public class OneToOneBidirectionalMappingTest {

    @Autowired
    private AddressRepository addressRepository;


    @Test
    void saveAddressMethod(){

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
        address.setOrder(order);

        addressRepository.save(address);

    }

    @Test
    void updateAddressMethod() {
        Address address = addressRepository.findById(1L).get();
        address.setZipCode("888222");

        address.getOrder().setStatus("DELIVERED");

        addressRepository.save(address);
    }

    @Test
    void fetchAddressMethod() {
        Address address = addressRepository.findById(1L).get();
    }


    @Test
    void deleteAddressMethod() {
        addressRepository.deleteById(1L);
    }


}
