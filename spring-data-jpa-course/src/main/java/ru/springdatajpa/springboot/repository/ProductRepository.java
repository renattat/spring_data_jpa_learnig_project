package ru.springdatajpa.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.springdatajpa.springboot.entity.Product;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {

    /**
     * Returns the found product entry by using its name as search criteria.
     * If no product is found, this method returns null.
     */
    Product findByName(String name);

    /**
     * Returns an Optional which contains the found product entry by
     * using its id as search criteria.
     * If no product entry is found, this method returns an empty Optional.
     */
    Optional<Product> findById(Long id);

    /**
     * Returns the found list of product entries whose name or description is given as
     * a method parameter. If no product entries is found, this method return an
     * empty list.
     */
    List<Product> findByNameOrDescription(String name, String description);

    /**
     * Returns the found list of product entries whose name and description is given as
     * a method parameter. If no product entries is found, this method return an
     * empty list.
     */
    List<Product> findByNameAndDescription(String name, String description);

    /**
     * Return the distinct product entry whose name is given as a method parameter
     * If no product entry is found, this method returns null

     */
    Product findDistinctByName(String name);

    /**
     * Return the products whose price is greater than given price as method parameter
     */
    List<Product> findByPriceGreaterThan(BigDecimal price);

    /**
     * Return the products whose price is less than given price as method parameter
     */
    List<Product> findByPriceLessThan(BigDecimal price);

    /**
     * Return the filtered the product records that match the given text
     */
    List<Product> findByNameContaining(String text);

    // Containing works the same as Like
    /**
     * Return products based on SQL like condition
     */
    List<Product> findByNameLike(String text);

    /**
     * Returns products whose price between start price and end price;
     */
    List<Product> findByPriceBetween(BigDecimal startPrice, BigDecimal endPrice);

    /**
     * Returns a products whose dateCreated between start date and end date
     */
    List<Product> findByDateCreatedBetween(LocalDateTime startDate, LocalDateTime endTime);

    /**
     * Returns list of products based on multiple values
     */
    List<Product> findByNameIn(List<String> value);

    List<Product> findFirst2ByOrderByNameAsc();

    //Top works the same as First
    List<Product> findTop3ByOrderByPriceAsc();

    // Define JPQL query using @Query annotation with index or position parameters
    @Query("SELECT p from Product p where p.name = ?1 or p.description = ?2")
    Product findByNameOrDescriptionParam(String name, String description);

    // Define JPQL query using @Query annotation with Named parameters
    @Query("SELECT p from Product p where p.name =:name or p.description = :description")
    Product findByNameOrDescriptionJPQLNamedParam(@Param("name") String name,
                                                  @Param("description") String description);

    // Define Native SQL query using @Query annotation with index or position parameters
    @Query(value = "SELECT * from products p where p.name = ?1 or p.description = ?2", nativeQuery = true)
    Product findByNameOrDescriptionSQLIndexParam(String name, String description);

    // Define Native SQL query using @Query annotation with named parameters
    @Query(value = "SELECT * from products p where p.name =:name or p.description = :description", nativeQuery = true)
    Product findByNameOrDescriptionSQLNamedParam(@Param("name") String name,
                                                 @Param("description") String description);

    // Define Named JPQL query
    Product findByPrice(@Param("price") BigDecimal price);

    List<Product> findAllOrderByNameDesc();

    // Define Named native SQL query
    @Query(nativeQuery = true)
    Product findByDescription(String description);

    List<Product> findAllOrderByNameASC();
}
