package com.example.accountspringdatajpa.entity;

import com.example.accountspringdatajpa.entity.enums.ProductSimpleStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private BigDecimal price;
    private String thumbnail;
    private String detail;
    private LocalDateTime createdAt;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name="category_id", nullable = false)
    private Category category;
    @Column(updatable = false, insertable = false)
    private Long category_id;
    @Enumerated(EnumType.ORDINAL)
    private ProductSimpleStatus status;
}
