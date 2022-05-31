package com.example.accountspringdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Embeddable
public class OrderDetailId implements Serializable {  //sử dụng serializable sử dụng để có thể dọc, ghi ra file, một class có composite key buộc phải cso @Embeddable và implements Serializable
    @Column(name = "order_id")
    private String orderId;

    @Column(name = "product_id")
    private Integer productId;
}
