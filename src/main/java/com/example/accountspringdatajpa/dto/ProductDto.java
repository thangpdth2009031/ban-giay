package com.example.accountspringdatajpa.dto;

import com.example.accountspringdatajpa.entity.Product;
import com.example.accountspringdatajpa.entity.enums.ProductSimpleStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDto {
    private Long id;
    private String name;
    private BigDecimal price;
    private String detail;
    private ProductSimpleStatus status;
    private String thumbnail;
    private CategoryDto category;

    public ProductDto(Product product) {
        id = product.getId();
        name = product.getName();
        price = product.getPrice();
        detail = product.getDetail();
        thumbnail = product.getThumbnail();
        status = product.getStatus();
        category = new CategoryDto(product.getCategory());
    }
}
