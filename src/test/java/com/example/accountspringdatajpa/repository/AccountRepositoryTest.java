package com.example.accountspringdatajpa.repository;

import com.example.accountspringdatajpa.AccountSpringDataJpaApplication;
import com.example.accountspringdatajpa.config.H2JpaConfig;
import com.example.accountspringdatajpa.entity.*;
import com.example.accountspringdatajpa.entity.enums.ProductSimpleStatus;
import com.github.javafaker.Faker;
import org.junit.Before;
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
public class AccountRepositoryTest {
    Faker faker = new Faker();
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AccountRepository accountRepository;

    @Before
    public void beforeProduct() throws Exception {
        System.out.println("before");
        Account account = new Account();
        account.setFirstName("firstName");
        account.setLastName("lastName");
        account.setPhone("978787798");
        account.setFullName("fullName");
        account.setAddress("address");
        account.setStatus(ProductSimpleStatus.ACTIVE);
        accountRepository.save(account);

        Account account1 = new Account();
        account.setFirstName("firstName1");
        account.setLastName("lastName1");
        account.setPhone("9787877981");
        account.setFullName("fullName1");
        account.setAddress("address1");
        account.setStatus(ProductSimpleStatus.ACTIVE);
        accountRepository.save(account1);
    }

    @Before
    public void beforeCategory() throws Exception {
        Category category = new Category();
        category.setTitle("title1");
        categoryRepository.save(category);
        Category category1 = new Category();
        category.setTitle("title2");
        categoryRepository.save(category1);
    }
    @Test
    public void beforeProduct1() throws Exception {
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
    }

//    @Test
//    public void saveOrder() throws Exception {
//        Order order = new Order();
//        String orderId = UUID.randomUUID().toString();
//        order.setId(orderId);
//        order.setTotalPrice(new BigDecimal(0));
//
//        Set<OrderDetail> orderDetailSet = new HashSet<>();
//        Product product01 = productRepository.findById(1L).get();
//        OrderDetail orderDetail = new OrderDetail();
//        orderDetail.setId(new OrderDetailId(orderId, product01.getId()));
//        orderDetail.setOrder(order);
//        orderDetail.setProduct(product01);
//        orderDetail.setQuantity(3);
//        orderDetail.setUnitPrice(product01.getPrice());
//        order.setTotalPrice(product01.getPrice().multiply(new BigDecimal(orderDetail.getQuantity())));
//        orderDetailSet.add(orderDetail);
//
//        Product product02 = productRepository.findById(2L).get();
//        OrderDetail orderDetail2 = new OrderDetail();
//        orderDetail2.setId(new OrderDetailId(orderId, product02.getId()));
//        orderDetail2.setOrder(order);
//        orderDetail2.setProduct(product02);
//        orderDetail2.setQuantity(5);
//        orderDetail2.setUnitPrice(product02.getPrice());
//        order.setTotalPrice(order.getTotalPrice().add(product02.getPrice().multiply(new BigDecimal(orderDetail2.getQuantity()))));
//        orderDetailSet.add(orderDetail2);
//
//        order.setOrderDetails(orderDetailSet);
//        orderRepository.save(order);
//
//        Order savedOrder = orderRepository.findAll().get(0);
//        System.out.println(savedOrder.getId());
//        System.out.println(savedOrder.getTotalPrice());
//        System.out.println(savedOrder.getOrderDetails().size());
//        for (OrderDetail od :
//                savedOrder.getOrderDetails()) {
//            System.out.println(od.getQuantity());
//            System.out.println(od.getUnitPrice());
//            System.out.println(od.getProduct().getName());
//        }
//    }
}
