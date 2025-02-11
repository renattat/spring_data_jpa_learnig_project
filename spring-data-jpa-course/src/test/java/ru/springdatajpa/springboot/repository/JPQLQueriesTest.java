package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Product;

@SpringBootTest
public class JPQLQueriesTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameOrDescriptionParam(){
        Product product = productRepository.findByNameOrDescriptionParam("product 1", "product 1 description");
        System.out.println(product);
    }

    @Test
    void findByNameOrDescriptionJPQLNamedParamMethod() {
        Product product = productRepository.findByNameOrDescriptionJPQLNamedParam(
                "product 1",  "product 1 description");
        System.out.println(product);
    }
}
