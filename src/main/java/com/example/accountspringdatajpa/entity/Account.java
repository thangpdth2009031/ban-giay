package com.example.accountspringdatajpa.entity;


import com.example.accountspringdatajpa.entity.enums.ProductSimpleStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String firstName;
    private String lastName;
    private String fullName;
    private String email;
    private String address;
    private String phone;
    @CreationTimestamp
    private Date createdAt;
    @UpdateTimestamp
    private Date updatedAt;
    @Enumerated(EnumType.ORDINAL)
    private ProductSimpleStatus status;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private Set<Order> orders;
    @OneToOne(mappedBy = "user")
    @JsonBackReference
    private ShoppingCart shoppingCart;
    private String password; // đã mã hoá. salt+passwordhash (md5, sha)
    private String username; // đã mã hoá. salt+passwordhash (md5, sha)
    @ManyToOne
    @JoinColumn(name = "role_id")
    @JsonBackReference
    private Role role;
    private String verifyCode;
}
