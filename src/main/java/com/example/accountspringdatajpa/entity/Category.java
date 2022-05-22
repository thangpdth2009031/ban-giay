package com.example.accountspringdatajpa.entity;

import com.example.accountspringdatajpa.dto.CategoryDto;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Product> products = new ArrayList<>();
/*    public void addProduct(Product product) {
        if (!products.contains(product)){
            products.add(product);
            product.setCategory(this);
        }
    }*/
}
