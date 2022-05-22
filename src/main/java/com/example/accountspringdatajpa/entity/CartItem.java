package com.example.accountspringdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "cart_items")
public class CartItem {
    @EmbeddedId
    private CartItemId id = new CartItemId();
    private String productName; // đỡ truy vấn ngược
    private String productImage;
    private int quantity;
    private BigDecimal unitPrice;
    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("shoppingCartId")
    @JoinColumn(name = "shopping_cart_id")
    private ShoppingCart shoppingCart;
}
