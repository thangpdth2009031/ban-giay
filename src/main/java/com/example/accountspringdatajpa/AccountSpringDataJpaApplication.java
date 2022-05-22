package com.example.accountspringdatajpa;

import com.example.accountspringdatajpa.entity.Category;
import com.example.accountspringdatajpa.entity.Product;
import com.example.accountspringdatajpa.repository.CategoryRepository;
import com.example.accountspringdatajpa.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class AccountSpringDataJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountSpringDataJpaApplication.class, args);
    }
//    @Bean
//    CommandLineRunner commandLineRunner(
//            CategoryRepository categoryRepository,
//            ProductRepository productRepository
//    ){
//        return args -> {
//            Category fatFood = new Category("Fat Food");
//            Category fatFood1 = new Category("Fat Food1");
//            Category fatFood2 = new Category("Fat Food2");
//            Product product = new Product("Chicken", 35.0);
//            Product product1 = new Product("Chicken1", 36.0);
//            Product product2 = new Product("Chicken2", 37.0);
//            Product product3 = new Product("Chicken3", 38.0);
//            fatFood.addProduct(product);
//            fatFood.addProduct(product1);
//            fatFood1.addProduct(product2);
//            fatFood2.addProduct(product3);
//            categoryRepository.save(fatFood);
//            categoryRepository.save(fatFood1);
//            categoryRepository.save(fatFood2);
//            productRepository.save(product);
//            productRepository.save(product1);
//            productRepository.save(product2);
//            productRepository.save(product3);
//        };
//    }

}
