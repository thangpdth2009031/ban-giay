package com.example.accountspringdatajpa.specification;

import com.example.accountspringdatajpa.entity.Account;
import com.example.accountspringdatajpa.entity.Order;
import com.example.accountspringdatajpa.entity.OrderDetail;
import com.example.accountspringdatajpa.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrderSpecification implements Specification<Order> {
    private SearchCriteria criteria;

    public OrderSpecification(SearchCriteria searchCriteria) {
        this.criteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (criteria.getOperator()) {
            case EQUALS:
                return criteriaBuilder.equal(
                        root.get(criteria.getKey()),
                        criteria.getValue());
            case GREATER_THAN:
                return criteriaBuilder.greaterThan(
                        root.get(criteria.getKey()),
                        String.valueOf(criteria.getValue()));
            case GREATER_THAN_OR_EQUALS:
                if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), (LocalDate)criteria.getValue());
                } else {
                    return criteriaBuilder.greaterThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
                }
            case LESS_THAN:
                return criteriaBuilder.lessThan(
                        root.get(criteria.getKey()),
                        String.valueOf(criteria.getValue()));
            case LESS_THAN_OR_EQUALS:
                if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
                    return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), (LocalDate)criteria.getValue());
                } else {
                    return criteriaBuilder.lessThanOrEqualTo(root.get(criteria.getKey()), criteria.getValue().toString());
                }
            case PRODUCT_JOIN_PRODUCT_NAME_LIKE:
                Join<OrderDetail, Product> orderDetailProductJoin = root.join("orderDetails").join("product");
                Predicate predicate = criteriaBuilder.or(
                        // t??m trong order b???n ghi c?? id gi???ng gi?? tr??? truy???n v??o
//                        criteriaBuilder.like(root.get("id"), "%" + criteria.getValue() + "%"),
                        // ho???c t??m trong b???ng product b???n ghi c?? name gi???ng v???i gi?? tr???
                        criteriaBuilder.like(orderDetailProductJoin.get(criteria.getKey()), "%" + criteria.getValue() + "%")
                );
                return predicate;
            case USER_JOIN_LIKE:
                From<Order, Account> accountOrderJoin = root.join("account");
                return criteriaBuilder.like(accountOrderJoin.get(criteria.getKey()), "%" + criteria.getValue() + "%");
        }
        return null;
    }


//    @Override
//    public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
//        if (criteria.getOperation().equalsIgnoreCase(">=")) {
//            if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
//                return criteriaBuilder.greaterThanOrEqualTo(
//                        root.get(criteria.getKey()), (LocalDate) criteria.getValue());
//            }else {
//                return criteriaBuilder.greaterThanOrEqualTo(
//                        root.get(criteria.getKey()), criteria.getValue().toString());
//            }
//        } else if (criteria.getOperation().equalsIgnoreCase("<=")) {
//            if (root.get(criteria.getKey()).getJavaType() == LocalDate.class) {
//                return criteriaBuilder.lessThanOrEqualTo(
//                        root.get(criteria.getKey()), (LocalDate) criteria.getValue());
//            }else {
//                return criteriaBuilder.lessThanOrEqualTo(
//                        root.get(criteria.getKey()), criteria.getValue().toString());
//            }
//        } else if (criteria.getOperation().equalsIgnoreCase("=")) {
//            if (root.get(criteria.getKey()).getJavaType() == String.class) {
//                return criteriaBuilder.like(
//                        root.get(criteria.getKey()), "%" + criteria.getValue() + "%");
//            } else {
//                return criteriaBuilder.equal(root.get(criteria.getKey()), criteria.getValue());
//            }
//        } else if (criteria.getOperation().equalsIgnoreCase("orderProduct")){
//            Join<OrderDetail, Product> orderDetailProductJoin = root.join("orderDetails").join("product");
//            return criteriaBuilder.or(
//                    // t??m trong order b???n ghi c?? id gi???ng gi?? tr??? truy???n v??o
//                    criteriaBuilder.like(root.get("id"), "%" + criteria.getValue() + "%"),
//                    // ho???c t??m trong b???ng product b???n ghi c?? name gi???ng v???i gi?? tr???
//                    criteriaBuilder.like(orderDetailProductJoin.get("name"), "%" + criteria.getValue() + "%")
//            );
//        } else if (criteria.getOperation().equalsIgnoreCase("fullName")) {
//            return criteriaBuilder.equal(root.get("account"), criteria.getValue());
//        }
//        return null;
//    }
}
