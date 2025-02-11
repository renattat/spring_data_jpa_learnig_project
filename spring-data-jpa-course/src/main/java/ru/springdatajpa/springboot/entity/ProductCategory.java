package ru.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "product_categories")
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String categoryName;
    private String categoryDescription;

    @OneToMany(cascade = {
            CascadeType.MERGE,
            CascadeType.PERSIST
    }, fetch = FetchType.LAZY,
    mappedBy = "category")
    private List<Product> products = new ArrayList<>();
}
