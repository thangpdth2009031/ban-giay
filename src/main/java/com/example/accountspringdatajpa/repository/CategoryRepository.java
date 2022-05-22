package com.example.accountspringdatajpa.repository;

import com.example.accountspringdatajpa.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
