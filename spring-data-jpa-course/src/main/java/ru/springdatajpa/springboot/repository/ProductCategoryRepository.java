package ru.springdatajpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.springdatajpa.springboot.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {
}
