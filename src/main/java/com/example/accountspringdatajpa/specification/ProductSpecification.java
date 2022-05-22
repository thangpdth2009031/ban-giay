package com.example.accountspringdatajpa.specification;

import com.example.accountspringdatajpa.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.time.LocalDate;

public class ProductSpecification implements Specification<Product> {
    private SearchCriteria criteria;

    public ProductSpecification(SearchCriteria name) {
        this.criteria = name;
    }
    @Override
    public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
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
//        }

        return null;
    }
}
