package ru.springdatajpa.springboot.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.springdatajpa.springboot.dto.OnlineOrderRequest;
import ru.springdatajpa.springboot.dto.OnlineOrderResponse;
import ru.springdatajpa.springboot.entity.OnlineOrder;
import ru.springdatajpa.springboot.entity.Payment;
import ru.springdatajpa.springboot.exception.PaymentException;
import ru.springdatajpa.springboot.repository.OnlineOrderRepository;
import ru.springdatajpa.springboot.repository.PaymentRepository;
import ru.springdatajpa.springboot.service.OnlineOrderService;

import java.util.UUID;

@Service
public class OnlineOrderServiceImpl implements OnlineOrderService {

    private OnlineOrderRepository onlineOrderRepository;
    private PaymentRepository paymentRepository;

    public OnlineOrderServiceImpl(OnlineOrderRepository onlineOrderRepository, PaymentRepository paymentRepository) {
        this.onlineOrderRepository = onlineOrderRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public OnlineOrderResponse placeOrder(OnlineOrderRequest onlineOrderRequest) {

        OnlineOrder onlineOrder = onlineOrderRequest.getOnlineOrder();
        onlineOrder.setStatus("IN_PROGRESS");
        onlineOrder.setOrderTrackingNumber(UUID.randomUUID().toString());
        onlineOrderRepository.save(onlineOrder);

        Payment payment = onlineOrderRequest.getPayment();

        if (!payment.getType().equals("DEBIT")) {
            throw new PaymentException("Payment card type do not support");
        }

        payment.setOnlineOrderId(onlineOrder.getId());
        paymentRepository.save(payment);

        OnlineOrderResponse onlineOrderResponse = new OnlineOrderResponse();
        onlineOrderResponse.setOrderTrackingNumber(onlineOrder.getOrderTrackingNumber());
        onlineOrderResponse.setStatus(onlineOrder.getStatus());
        onlineOrderResponse.setMessage("SUCCESS");
        return onlineOrderResponse;
    }
}
