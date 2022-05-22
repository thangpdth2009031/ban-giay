package com.example.accountspringdatajpa.service;
import com.example.accountspringdatajpa.entity.*;
import com.example.accountspringdatajpa.repository.OrderRepository;
import com.example.accountspringdatajpa.specification.*;
import com.example.accountspringdatajpa.util.HelpConvertDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Page<Order> findAllAndFilter(SearchBody searchBody){
        Specification specification = Specification.where(null);
        if (searchBody.getStatus() != -1){
            specification = specification.and(new OrderSpecification(new SearchCriteria("status", SearchCriteriaOperator.EQUALS, searchBody.getStatus())));
        }
        if (searchBody.getStartDate() != null && !searchBody.getStartDate().equals("")){
            LocalDateTime startDate = HelpConvertDate.toLocalDateTime(searchBody.getStartDate(), true);
            specification = specification.and(new OrderSpecification(new SearchCriteria("createdAt", SearchCriteriaOperator.GREATER_THAN_OR_EQUALS,startDate)));
        }
        if (searchBody.getEndDate() != null && !searchBody.getEndDate().equals("")){
            LocalDateTime endDate = HelpConvertDate.toLocalDateTime(searchBody.getEndDate(), false);
            specification = specification.and(new OrderSpecification(new SearchCriteria("createdAt", SearchCriteriaOperator.LESS_THAN_OR_EQUALS,endDate)));
        }
        if (searchBody.getOrderId()!= null && !searchBody.getOrderId().equals("")){
            specification = specification.and(new OrderSpecification(new SearchCriteria("id", SearchCriteriaOperator.EQUALS,searchBody.getOrderId().trim())));
        }
        if (searchBody.getProductName()!= null && !searchBody.getProductName().equals("")){
            specification = specification.and(new OrderSpecification(new SearchCriteria("", SearchCriteriaOperator.PRODUCT_JOIN_PRODUCT_NAME_LIKE,searchBody.getProductName().trim())));
        }
        if (searchBody.getCustomerName()!= null && !searchBody.getCustomerName().equals("")){
            specification = specification.and(new OrderSpecification(new SearchCriteria("fullName", SearchCriteriaOperator.USER_JOIN_LIKE, searchBody.getCustomerName().trim())));
        }
        if (searchBody.getCustomerPhone()!= null && !searchBody.getCustomerPhone().equals("")){
            specification = specification.and(new OrderSpecification(new SearchCriteria("phone", SearchCriteriaOperator.USER_JOIN_LIKE, searchBody.getCustomerPhone().trim())));
        }
        if (searchBody.getCustomerEmail()!= null && !searchBody.getCustomerEmail().equals("")){
            specification = specification.and(new OrderSpecification(new SearchCriteria("email", SearchCriteriaOperator.USER_JOIN_LIKE, searchBody.getCustomerEmail().trim())));
        }

        List<Sort.Order> orders = new ArrayList<>();
        Sort.Order order;
        order = new Sort.Order(Sort.Direction.DESC, "createdAt");
        if (searchBody.getTimeSorting() !=null){
            if (searchBody.getTimeSorting().contains("oldest")){
                order = new Sort.Order(Sort.Direction.ASC, "createdAt");
            }

        }
        if (searchBody.getPriceSorting() != null) {
            Sort.Order order1;
            if (searchBody.getPriceSorting().contains("descending")) {
                order1 = new Sort.Order(Sort.Direction.DESC, "totalPrice");
            } else {
                order1 = new Sort.Order(Sort.Direction.ASC, "totalPrice");
            }
            orders.add(order1);
        }
        orders.add(order);
        Pageable pageable = PageRequest.of(searchBody.getPage() -1, searchBody.getLimit(), Sort.by(orders));
        return (Page<Order>) orderRepository.findAll(specification, pageable);
    }
}
