package com.example.accountspringdatajpa.dto;

import java.math.BigDecimal;

public class OrderDto {
    private String id;
    private String fullName;
    private BigDecimal totalPrice;
    private OrderDetailDto orderDetailDto;
}
