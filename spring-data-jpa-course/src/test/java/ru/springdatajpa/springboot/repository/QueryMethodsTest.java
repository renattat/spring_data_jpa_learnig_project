package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class QueryMethodsTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void findByNameMethod() {
        Product product = productRepository.findByName("product 2");

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByIdMethod() {
        Product product = productRepository.findById(1L).get();

        System.out.println(product.getId());
        System.out.println(product.getName());
        System.out.println(product.getDescription());
    }

    @Test
    void findByNameOrDescriptionMethod() {
        List<Product> products = productRepository.findByNameOrDescription("product 3",
                "product 2 description");

        products.forEach(product -> System.out.println(product));
    }

    @Test
    void findByNameAndDescriptionMethod() {
        List<Product> products = productRepository.findByNameAndDescription("product 2",
                "product 2 description");


    }

    @Test
    void findDistinctByNameMethod() {
        Product product = productRepository.findDistinctByName("product 1");

        System.out.println(product);
    }

    @Test
    void findByPriceGreaterThanMethod() {
        List<Product> products = productRepository.findByPriceGreaterThan(new BigDecimal(100));

        products.forEach(product -> System.out.println("\n" + product + "\n"));
    }

    @Test
    void findByPriceLessThanMethod() {
        List<Product> products = productRepository.findByPriceLessThan(new BigDecimal(300));

        products.forEach(product -> System.out.println("\n" + product + "\n"));
    }

    @Test
    void findByNameContainingMethod() {
        List<Product> products = productRepository.findByNameContaining("rodu");

        products.forEach(product -> System.out.println("\n" + product + "\n"));
    }

    @Test
    void findByNameLikeMethod() {
        List<Product> products = productRepository.findByNameLike("rodu");

        products.forEach(product -> System.out.println("\n" + product + "\n"));
    }

    @Test
    void findByPriceBetweenMethod() {
        List<Product> products = productRepository.findByPriceBetween(
                new BigDecimal(50),
                new BigDecimal(299));

        products.forEach(product -> System.out.println("\n" + product + "\n"));
    }

    @Test
    void findByDateCreatedBetweenMethod() {
        // start date
        LocalDateTime startDate = LocalDateTime.of(2025, 1, 1, 1, 0, 0);
        // end date

        LocalDateTime endDate = LocalDateTime.of(2025, 2, 1, 1, 0, 0);

        List<Product> products = productRepository.findByDateCreatedBetween(startDate, endDate);

        products.forEach(product -> System.out.println("\n" + product + "\n"));

    }

    @Test
    void findByNameInMethod() {
        List<Product> products = productRepository.findByNameIn(List.of("product 1", "product 3"));
        products.forEach(product -> System.out.println("\n" + product + "\n"));
    }

    @Test
    void findFirst2tByOrderByNameAsc(){
        List<Product> products = productRepository.findFirst2ByOrderByNameAsc();
        products.forEach(product -> System.out.println("\n" + product + "\n"));
    }

    @Test
    void findTop3ByOrderByPriceAscMethod(){
        List<Product> products = productRepository.findTop3ByOrderByPriceAsc();
        products.forEach(product -> System.out.println("\n" + product + "\n"));

    }


}

