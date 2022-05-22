package com.example.accountspringdatajpa.restapi;

import com.example.accountspringdatajpa.dto.ProductDto;
import com.example.accountspringdatajpa.entity.Account;
import com.example.accountspringdatajpa.entity.Order;
import com.example.accountspringdatajpa.entity.Product;
import com.example.accountspringdatajpa.service.OrderService;
import com.example.accountspringdatajpa.service.ProductService;
import com.example.accountspringdatajpa.specification.OrderSpecification;
import com.example.accountspringdatajpa.specification.SearchBody;
import com.example.accountspringdatajpa.specification.SearchCriteria;
import com.example.accountspringdatajpa.specification.SearchCriteriaOperator;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/orders")
@CrossOrigin("http://localhost:8080")
public class OrderApi {
    final
    OrderService orderService;

    public OrderApi(OrderService orderService) {
        this.orderService = orderService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Map<String, Object>> getAllOrder(@RequestBody SearchBody searchBody) {
        try {
            List<Order> orders;
            Page<Order> pageTuts;
            pageTuts = orderService.findAllAndFilter(searchBody);
            orders = pageTuts.getContent();
            Map<String, Object> response = new HashMap<>();
            response.put("order", orders);
            response.put("currentPage", pageTuts.getNumber() + 1);
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
//    @RequestMapping(method = RequestMethod.GET)
//    public ResponseEntity<?> getAllOrderGet() {
//        return ResponseEntity.ok(orderService.findAll());
//    }
}
