package ru.springdatajpa.springboot.contorller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.springdatajpa.springboot.dto.OnlineOrderRequest;
import ru.springdatajpa.springboot.dto.OnlineOrderResponse;
import ru.springdatajpa.springboot.service.OnlineOrderService;

@RestController
@RequestMapping("/api/v1/onlineorders")
public class OnlineOrderController  {

    private OnlineOrderService onlineOrderService;

    public OnlineOrderController(OnlineOrderService onlineOrderService) {
        this.onlineOrderService = onlineOrderService;
    }

    @PostMapping
    public ResponseEntity<OnlineOrderResponse> placeOrder(@RequestBody OnlineOrderRequest orderRequest) {
        return ResponseEntity.ok(onlineOrderService.placeOrder(orderRequest));
    }
}
