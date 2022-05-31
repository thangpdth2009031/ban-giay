package com.example.accountspringdatajpa.repository;

import com.example.accountspringdatajpa.dto.ProductDto;
import com.example.accountspringdatajpa.entity.Product;
import com.example.accountspringdatajpa.specification.ProductSpecification;
import com.example.accountspringdatajpa.specification.SearchBody;
import com.example.accountspringdatajpa.specification.SearchCriteria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>, JpaSpecificationExecutor<Product> {
    Page<Product> findAll(Pageable pageable);
//    Page<Product> findAllAndFilter(SearchBody searchBody){
//        Specification specification = Specification.where(null);
//        if (searchBody.getKeyword() != null){
//            specification = specification.and(new ProductSpecification(new SearchCriteria("name",":", searchBody.getKeyword())));
//        }
//        if (searchBody.getCategoryId() != -1){
//            specification = specification.and(new ProductSpecification(new SearchCriteria("category_id", "=",searchBody.getCategoryId())));
//        }
//        if (!searchBody.getStart().isEmpty()){
//            LocalDate date = HelpConvertDate.convertStringToLocalDate(searchBody.getStart());
//            specification = specification.and(new ProductSpecification(new SearchCriteria("created_at", ">=",date)));
//        }
//        if (!searchBody.getEnd().isEmpty()){
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
//        Page<Student> students = studentRepository.findAll(specification,pageable);
//        return students;
//    }
    @Query("SELECT e FROM Product e where e.name LIKE %:name%")
    Page<Product> searchAllProduct(String name, Pageable pageable);

    Product findByName(String name);
    @Query("SELECT e FROM Product e where e.name LIKE %:name%")
    List<Product> findNameProduct(@Param("name") String name);
}
