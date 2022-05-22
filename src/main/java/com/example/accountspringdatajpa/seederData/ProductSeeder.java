package com.example.accountspringdatajpa.seederData;

import com.example.accountspringdatajpa.entity.*;
import com.example.accountspringdatajpa.entity.enums.ProductSimpleStatus;
import com.example.accountspringdatajpa.repository.*;
import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

//@Component
public class ProductSeeder implements CommandLineRunner {
    Faker faker = new Faker();
    @Autowired
    ProductRepository productRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderDetailRepository orderDetailRepository;

    @Override
    public void run(String... args) throws Exception {
        seedAccount();
        seedCategory();
        seedProduct();
        seedOrder();
    }

    public void seedCategory() {
        List<Category> categories = new ArrayList<>();
        for (int i = 0; i <= 100; i++) {
            String cteTitle = faker.funnyName().name();
            for (Category category :
                    categories) {
                if (category.getTitle().equals(cteTitle)) {
                    break;
                }
            }
            Category category = new Category();
            category.setTitle(cteTitle);
            categories.add(category);
        }
        categoryRepository.saveAll(categories);
    }

    //Lọc theo tên sản phẩm, tên ngươif dùng, trong khoangr thời gian, trong khoảng giá,
    public void seedProduct() {
        List<Product> products = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            int category_id = faker.number().numberBetween(1, 30);
            String nameProduct = faker.book().title();
            Category category = categoryRepository.findById((long)category_id).get();
            Product product = new Product();
            product.setName(nameProduct);
            product.setPrice(BigDecimal.valueOf(faker.number().numberBetween(200000, 300000)));
            product.setStatus(ProductSimpleStatus.ACTIVE);
            product.setThumbnail(faker.avatar().image());
            product.setDetail(faker.lorem().toString());
            product.setCategory_id((long)category_id);
            product.setCategory(category);
            product.setCreatedAt(LocalDateTime.now());
            products.add(product);
        }
        productRepository.saveAll(products);
    }

    public void seedAccount() {
        List<Account> accounts = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String fullName = firstName + " " + lastName;
            Account account = new Account();
            account.setFirstName(firstName);
            account.setLastName(lastName);
            account.setPhone(faker.phoneNumber().phoneNumber());
            account.setEmail(faker.name().username());
            account.setFullName(fullName);
            account.setAddress(faker.address().streetAddress());
            account.setStatus(ProductSimpleStatus.ACTIVE);
            accounts.add(account);
        }
        accountRepository.saveAll(accounts);
    }

    public void seedOrder() {
        List<Order> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();
        boolean existProduct = false;
        for (int i = 1; i <= 100; i++) {
            Order order = new Order();

            long total = 0;
            int orderDetailNumber = faker.number().numberBetween(1, 5);
            order.setStatus(ProductSimpleStatus.ACTIVE);
            int userId = faker.number().numberBetween(1, 100);
            order.setUserId((long) userId);
            order.setAccount(accountRepository.findById((long) userId).get());
            for (int j = 0; j < orderDetailNumber; j++) {
                int productId = faker.number().numberBetween(1, 100);
                for (OrderDetail od :
                        orderDetails) {
                    if (od.getProduct().getId() == productId && od.getOrder().getAccount().getId() == userId) {
                        existProduct = true;
                        break;
                    }
                }
                if (existProduct) {
                    j--;
                    existProduct = false;
                    continue;
                }
                OrderDetail orderDetail = new OrderDetail();
                Product product = productRepository.findById((long) productId).get();
                int quantity = faker.number().numberBetween(1, 5);
                orderDetail.setProduct(product);
                orderDetail.setOrder(order);
                orderDetail.setQuantity(quantity);
                long unitPrice = (long) quantity * product.getPrice().intValue();
                orderDetail.setUnitPrice(new BigDecimal(unitPrice));
                total += unitPrice;
                orderDetails.add(orderDetail);
            }
            order.setCreatedAt(LocalDateTime.now());
            order.setTotalPrice(new BigDecimal(total));
            orders.add(order);
            if (i % 20 == 0) {
                orderRepository.saveAll(orders);
                orderDetailRepository.saveAll(orderDetails);
                orders.clear();
                orderDetails.clear();
            }
        }
    }
}
