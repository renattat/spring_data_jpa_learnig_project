package ru.springdatajpa.springboot.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@ToString

// @Data = @Getter + @Setter + @AllArgsConstructor + @NoArgsConstructor + @ToString
@Data
@Entity
@Table(name = "orders")
public class Order  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orderTrackingNumber;
    private int totalQuantity;
    private BigDecimal totalPrice;
    private String status;

    @CreationTimestamp
    private LocalDateTime dateCreated;
    @UpdateTimestamp
    private LocalDateTime lastUpdated;

    // default fetch type for one-to-one mapping is EAGER
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "order")
    private Address billingAddress;

    // default fetch type for one to many is LAZY
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.EAGER,
            mappedBy = "order")
    private Set<OrderItem> orderItems = new HashSet<>();

    public BigDecimal getTotalAmount(){
        BigDecimal amount = new BigDecimal(0.0);
        for(OrderItem item: orderItems) {
            amount = amount.add(item.getPrice());
        }
        return amount;
    }
}
