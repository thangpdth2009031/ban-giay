package com.example.accountspringdatajpa.repository;

import com.example.accountspringdatajpa.entity.OrderDetail;
import com.example.accountspringdatajpa.entity.OrderDetailId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, OrderDetailId> {
}
