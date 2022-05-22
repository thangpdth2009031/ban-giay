package com.example.accountspringdatajpa.restapi;

import com.example.accountspringdatajpa.dto.ShoppingCartDTO;
import com.example.accountspringdatajpa.entity.ShoppingCart;
import com.example.accountspringdatajpa.repository.ProductRepository;
import com.example.accountspringdatajpa.repository.ShoppingCartRepository;
import com.example.accountspringdatajpa.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping(path = "api/v1/shopping-carts")
public class ShoppingCartApi {
    @Autowired
    ShoppingCartRepository shoppingCartRepository;

    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShoppingCartService shoppingCartService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ShoppingCart> saveCart(@RequestBody ShoppingCart shoppingCart){
        ShoppingCart shoppingCart1 = shoppingCartService.saveCart(shoppingCart);
        return ResponseEntity.ok(shoppingCart1);
    }
}
