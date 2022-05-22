package com.example.accountspringdatajpa.service;

import com.example.accountspringdatajpa.entity.OrderDetail;
import com.example.accountspringdatajpa.repository.OrderDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    public List<OrderDetail> findAll() {
        return orderDetailRepository.findAll();
    }
}
