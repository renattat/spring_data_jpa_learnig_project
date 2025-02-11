package ru.springdatajpa.springboot.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.*;
import ru.springdatajpa.springboot.entity.Product;

import java.util.List;

@SpringBootTest
public class PaginationAndSortingTest {

    @Autowired
    private ProductRepository productRepository;

    @Test
    void pagination() {
        int pageNo = 0;
        int pageSize = 5;

        // create pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize);

        // findAll method and pass pageable instance

        Page<Product> page = productRepository.findAll(pageable);
        page.getContent().forEach(element -> System.out.println(element + "\n"));

        // total pages
        int totalPage = page.getTotalPages();
        // total elements
        long totalElements = page.getTotalElements();
        // number if elements
        int numberOfElements = page.getNumberOfElements();
        // size
        int size = page.getSize();
        // last
        boolean isLast = page.isLast();
        // first
        boolean isFirst = page.isFirst();

        System.out.println("total page -> " + totalPage);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println("size -> " + size);
        System.out.println("isLast -> " + isLast);
        System.out.println("isFirst -> " + isFirst);
    }

    @Test
    void sorting() {
        String sortBy = "price";
        String sortDir = "desc";

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

//        List<Product> products = productRepository.findAll(Sort.by(sortBy).ascending());
        List<Product> products = productRepository.findAll(sort);
        products.forEach(product -> System.out.println(product + "\n"));
    }

    @Test
    void sortingByMultipleFields() {
        String sortBy = "name";
        String sortByDescrip = "description";
        String sortDir = "desc";

        Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        Sort sortByDescription = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortByDescrip).ascending() : Sort.by(sortByDescrip).descending();


        Sort groupBySort = sortByName.and(sortByDescription);

        List<Product> products = productRepository.findAll(groupBySort);
        products.forEach(product -> System.out.println(product + "\n"));
    }

    @Test
    void paginationAndSortingTogether() {
        String sortBy = "price";
        String sortDir = "desc";

        int pageNo = 1;
        int pageSize = 5;

        // Sort object
        Sort sortByName = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ?
                Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // Pageable object
        Pageable pageable = PageRequest.of(pageNo, pageSize, sortByName);

        Page<Product> page = productRepository.findAll(pageable);

        page.getContent().forEach(product -> System.out.println(product + "\n"));

        int totalPage = page.getTotalPages();
        long totalElements = page.getTotalElements();
        int numberOfElements = page.getNumberOfElements();
        int size = page.getSize();
        boolean isLast = page.isLast();
        boolean isFirst = page.isFirst();

        System.out.println("total page -> " + totalPage);
        System.out.println("totalElements -> " + totalElements);
        System.out.println("numberOfElements -> " + numberOfElements);
        System.out.println("size -> " + size);
        System.out.println("isLast -> " + isLast);
        System.out.println("isFirst -> " + isFirst);
    }
}
