package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.springdatajpa.springboot.entity.Product;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;


    @Test
    void saveMethod() {
        // create product
        Product product = new Product();
        product.setName("product 1");
        product.setDescription("product 1 description");
        product.setSku("100ABC");
        product.setPrice(new BigDecimal(100));
        product.setActive(true);
        product.setImageUrl("product.png");

        // save product
        Product savedObject = productRepository.save(product);

        // display product info
        System.out.println(savedObject.getId());
        System.out.println(savedObject);

    }

    @Test
    void updateUsingSaveMethod() {
        // find or retrieve an entity by id
        Long id = 1L;
        Product product = productRepository.findById(id).get();

        // update entity information
        product.setName("updated product1");
        product.setDescription("updated product 1 desc");

        // save updated entity
        productRepository.save(product);
    }

    @Test
    void findByIdMethod() {
        Long id = 1l;
        Product product = productRepository.findById(id).get();
    }

    @Test
    void saveAllMethod() {

        // create product
        Product product = new Product();
        product.setName("product 2");
        product.setDescription("product 2 description");
        product.setSku("100ABCD");
        product.setPrice(new BigDecimal(200));
        product.setActive(true);
        product.setImageUrl("product2.png");

        // create product
        Product product3 = new Product();
        product3.setName("product 3");
        product3.setDescription("product 3 description");
        product3.setSku("100ABCDE");
        product3.setPrice(new BigDecimal(300));
        product3.setActive(true);
        product3.setImageUrl("product3.png");

        productRepository.saveAll(List.of(product, product3));
    }

    @Test
    void findAllMethod() {
        List<Product> products = productRepository.findAll();

        products.forEach(product -> System.out.println(product.toString()));
    }

    @Test
    void deleteByIdMethod() {
        Long id = 1L;

        productRepository.deleteById(id);
    }

    @Test
    void deleteMethod() {
        // find an entity by id
        Long id = 3l;
        Product product = productRepository.findById(id).get();
        // delete(entity)

        productRepository.delete(product);
    }

    @Test
    void deleteAllMethod() {
//        productRepository.deleteAll();

        Product product = productRepository.findById(6L).get();
        Product product1 = productRepository.findById(7L).get();

        productRepository.deleteAll(List.of(product, product1));
    }


    @Test
    void countMethod(){
        long count = productRepository.count();
        System.out.println("count is ---> " + count);
    }

    @Test
    void existById() {
        Long id = 8L;

        boolean result = productRepository.existsById(id);
        System.out.println("\nexist by id ->>> " + result);
    }




}