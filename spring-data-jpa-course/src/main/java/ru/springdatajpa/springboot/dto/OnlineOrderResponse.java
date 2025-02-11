package ru.springdatajpa.springboot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OnlineOrderResponse {
    private String orderTrackingNumber;
    private String status;
    private String message;
}
