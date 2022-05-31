package com.example.accountspringdatajpa.service;


import com.example.accountspringdatajpa.dto.CategoryDto;
import com.example.accountspringdatajpa.dto.ProductDto;
import com.example.accountspringdatajpa.entity.Category;
import com.example.accountspringdatajpa.entity.Product;
import com.example.accountspringdatajpa.entity.enums.ProductSimpleStatus;
import com.example.accountspringdatajpa.exception.NotFoundException;
import com.example.accountspringdatajpa.repository.CategoryRepository;
import com.example.accountspringdatajpa.repository.ProductRepository;
import com.example.accountspringdatajpa.specification.ProductSpecification;
import com.example.accountspringdatajpa.specification.SearchBody;
import com.example.accountspringdatajpa.specification.SearchCriteria;
import com.example.accountspringdatajpa.util.HelpConvertDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CategoryRepository categoryRepository;

    public Product findByName(String name) {
        return productRepository.findByName(name);
    }
    public List<ProductDto> searchName(String name) {
        List<Product> products = productRepository.findNameProduct(name);
        List<ProductDto> listProducts = new ArrayList<>();
        for (Product product: products) {
            listProducts.add(new ProductDto(product));
        }
        return listProducts;
    }
//    public Page<Product> findAllAndFilter(SearchBody searchBody){
//        Specification specification = Specification.where(null);
//        if (searchBody.getKeyword() != null){
//            specification = specification.and(new ProductSpecification(new SearchCriteria("name","=", searchBody.getKeyword())));
//        }
//        if (searchBody.getCategoryId() != -1){
//            specification = specification.and(new ProductSpecification(new SearchCriteria("category_id", "=",searchBody.getCategoryId())));
//        }
//        if (searchBody.getStart() != null){
//            LocalDate date = HelpConvertDate.convertStringToLocalDate(searchBody.getStart());
//            specification = specification.and(new ProductSpecification(new SearchCriteria("created_at", ">=",date)));
//        }
//        if (searchBody.getEnd()!= null){
//            LocalDate date = HelpConvertDate.convertStringToLocalDate(searchBody.getEnd());
//            specification = specification.and(new ProductSpecification(new SearchCriteria("created_at", "<=",date)));
//        }
//
//        Sort sort= Sort.by(Sort.Order.asc("id"));
//        if (searchBody.getSort() !=null){
//            if (searchBody.getSort().contains("desc")){
//                sort = Sort.by(Sort.Order.desc("id"));
//            }
//
//        }
//        Pageable pageable = PageRequest.of(searchBody.getPage() -1, searchBody.getLimit(),sort );
//        Page<Product> products = productRepository.findAll(specification,pageable);
//        return products;
//    }

//    public List<ProductDto> findAll() {
//        List<Product> products = productRepository.findAll();
//        List<ProductDto> listProducts = new ArrayList<>();
//        for (Product product: products) {
//            listProducts.add(new ProductDto(product));
//        }
//        return listProducts;
//    }

    public Optional<Product> findById(Integer id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()) {
            throw new NotFoundException("khong tim thay product");
        }
        return product;
    }

    public Product save(Product product) {
        Category category = categoryRepository.findById(product.getCategory_id()).orElse(null);
        if (category == null) {
            throw new NotFoundException("không thuộc danh mục nào.");
        }
        else {
            product.setCategory(category);
            productRepository.save(product);
        }
        return product;
    }
    public void deleteById(Integer id) {
        productRepository.deleteById(id);
    }
}
