package com.example.accountspringdatajpa.repository;

import com.example.accountspringdatajpa.entity.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer> {
    ShoppingCart findShoppingCartByUser_Id(Integer id);
}
