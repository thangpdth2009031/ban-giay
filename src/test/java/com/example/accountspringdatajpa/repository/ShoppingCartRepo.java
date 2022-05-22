package com.example.accountspringdatajpa.repository;

import com.example.accountspringdatajpa.AccountSpringDataJpaApplication;
import com.example.accountspringdatajpa.config.H2JpaConfig;
import com.example.accountspringdatajpa.entity.*;
import com.example.accountspringdatajpa.entity.enums.ProductSimpleStatus;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {AccountSpringDataJpaApplication.class, H2JpaConfig.class})
@ActiveProfiles("test")
public class ShoppingCartRepo {
    public ShoppingCartRepo() {
    }

    @Autowired
    AccountRepository accountRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    CategoryRepository categoryRepository;

    @Test
    public void addToCart() {


        Account account = new Account();
        account.setId((long)1);
        account.setFirstName("firstName");
        account.setLastName("lastName");
        account.setPhone("978787798");
        account.setFullName("fullName");
        account.setAddress("address");
        account.setStatus(ProductSimpleStatus.ACTIVE);
        accountRepository.save(account);

        Category category = new Category();
        category.setId((long) 1);
        category.setTitle("title1");
        categoryRepository.save(category);

        Product product = new Product();
        product.setId((long)1);
        product.setDetail("detail1");
        product.setThumbnail("title1");
        product.setStatus(ProductSimpleStatus.ACTIVE);
        product.setPrice(BigDecimal.valueOf(23));
        product.setCategory_id((long) 1);
        product.setCategory(category);
        productRepository.save(product);

        System.out.println("size = " + productRepository.findAll().size());

        String userAccessToken = "";
        String userId = "A001";
        boolean hasException = false;
        long accountId = 1;
        long productId = 1;
//        Optional<Product> optionalProduct = this.productRepository.findById(product.getId());
//        Product product1 = optionalProduct.get();
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setAccount(accountRepository.findById(account.getId()).get());
        shoppingCart.setUserId(account.getId());
        shoppingCart.setTotalPrice(BigDecimal.valueOf(1234));
        shoppingCart.setShipAddress("thanh son");
        shoppingCart.setShipNote("shipnote");
        shoppingCart.setShipName("thang");
        shoppingCart.setShipPhone("098899033");
        Set<CartItem> setCartItem = new HashSet();
        CartItem cartItem = new CartItem();
        cartItem.setId(new CartItemId(shoppingCart.getId(), product.getId()));
        cartItem.setQuantity(2);
        cartItem.setProductName(product.getName());
        cartItem.setProductImage(product.getThumbnail());
        cartItem.setShoppingCart(shoppingCart);
        setCartItem.add(cartItem);
        shoppingCart.setItems(setCartItem);
        shoppingCartRepository.save(shoppingCart);


//        while(var10.hasNext()) {
////            CartItemDTO cartItemDTO = (CartItemDTO)var10.next();
//            Optional<Product> optionalProduct = this.productRepository.findById(cartItemDTO.getProductId());
//            if (!optionalProduct.isPresent()) {
//                hasException = true;
//                break;
//            }
//
//            Product product = (Product)optionalProduct.get();
//            CartItem cartItem = CartItem.builder().id(new CartItemId(shoppingCart.getId(), product.getId())).productName(product.getName()).productImage(product.getThumbnails()).quantity(cartItemDTO.getQuantity()).unitPrice(product.getPrice()).shoppingCart(shoppingCart).build();
//            shoppingCart.addTotalPrice(cartItem);
//            setCartItem.add(cartItem);
//        }
    }
}
