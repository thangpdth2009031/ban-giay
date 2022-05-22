package com.example.accountspringdatajpa.dto;

import com.example.accountspringdatajpa.entity.OrderDetail;
import com.example.accountspringdatajpa.entity.Product;

import java.math.BigDecimal;

public class OrderDetailDto {
    private ProductDto product;
    private int quantity;
    private BigDecimal price;

    public OrderDetailDto(OrderDetail orderDetail) {
        quantity = orderDetail.getQuantity();
        price = orderDetail.getUnitPrice();
        product = new ProductDto(orderDetail.getProduct());
    }
}
