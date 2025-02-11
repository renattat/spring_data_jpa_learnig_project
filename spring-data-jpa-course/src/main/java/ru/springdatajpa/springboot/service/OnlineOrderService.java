package ru.springdatajpa.springboot.service;

import ru.springdatajpa.springboot.dto.OnlineOrderRequest;
import ru.springdatajpa.springboot.dto.OnlineOrderResponse;

public interface OnlineOrderService {

    OnlineOrderResponse placeOrder(OnlineOrderRequest onlineOrderRequest);
}
