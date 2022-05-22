package com.example.accountspringdatajpa.restapi;

import com.example.accountspringdatajpa.entity.Order;
import com.example.accountspringdatajpa.entity.OrderDetail;
import com.example.accountspringdatajpa.service.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/orderDetails")
public class OrderDetailApi {
    @Autowired
    OrderDetailService orderDetailService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetail>> getList() {
        return ResponseEntity.ok(orderDetailService.findAll());
    }
}
