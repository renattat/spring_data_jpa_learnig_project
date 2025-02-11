package ru.springdatajpa.springboot.dto;

import lombok.Getter;
import lombok.Setter;
import ru.springdatajpa.springboot.entity.OnlineOrder;
import ru.springdatajpa.springboot.entity.Payment;

@Getter
@Setter
public class OnlineOrderRequest {
    private OnlineOrder onlineOrder;
    private Payment payment;
}
