package com.example.accountspringdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "shopping_carts")
public class ShoppingCart {
    // ship về đâu, cho ai, địa chỉ, note ship khi nào
    // tổng tiền.
    // thời gian tạo.
    // ai tạo.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private Account user;
    private BigDecimal total;
    @OneToMany(mappedBy = "shoppingCart")
    @JsonManagedReference
    private Set<CartItem> cartItems;

}
