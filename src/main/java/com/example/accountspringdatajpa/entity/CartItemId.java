package com.example.accountspringdatajpa.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.criteria.CriteriaBuilder;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartItemId  implements Serializable {
    @Column(name = "shopping_cart_id")
    private Integer shoppingCartId; // thuộc về shopping Cart nào.
    @Column(name = "product_id")
    private Integer productId;
}
