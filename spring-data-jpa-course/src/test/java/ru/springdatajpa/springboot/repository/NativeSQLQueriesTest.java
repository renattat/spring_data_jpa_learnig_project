package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Product;

@SpringBootTest
public class NativeSQLQueriesTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionSQLIndexParamMethod(){
        Product product = productRepository.findByNameOrDescriptionSQLIndexParam("product 1", "product 1 description");
        System.out.println(product);
    }

    @Test
    void findByNameOrDescriptionSQLNamedParamMethod(){
        Product product = productRepository.findByNameOrDescriptionSQLNamedParam("product 1", "product 1 description");
        System.out.println(product);
    }


}
