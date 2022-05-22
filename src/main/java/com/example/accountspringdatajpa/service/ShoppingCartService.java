package com.example.accountspringdatajpa.service;

import com.example.accountspringdatajpa.entity.*;
import com.example.accountspringdatajpa.repository.AccountRepository;
import com.example.accountspringdatajpa.repository.ProductRepository;
import com.example.accountspringdatajpa.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartService {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    AccountRepository accountRepository;

    public ShoppingCart saveCart(ShoppingCart ShoppingCart) {

        return ShoppingCart;
    }
}

