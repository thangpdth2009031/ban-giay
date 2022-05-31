package com.example.accountspringdatajpa.restapi;

import com.example.accountspringdatajpa.dto.ProductDto;
import com.example.accountspringdatajpa.entity.Product;
import com.example.accountspringdatajpa.repository.ProductRepository;
import com.example.accountspringdatajpa.service.ProductService;
import com.example.accountspringdatajpa.specification.SearchBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/v1/products")
@CrossOrigin("http://localhost:8080")
public class ProductApi {
    @Autowired
    ProductService productService;
    @Autowired
    ProductRepository productRepository;

    /*    @RequestMapping(method = RequestMethod.GET)
        public ResponseEntity<?> getList(
                @RequestParam(name = "page", defaultValue = "1") int page,
                @RequestParam(name = "limit", defaultValue = "10") int limit
        ) {
            return ResponseEntity.ok(productService.findAll(PageRequest.of(page - 1, limit)));
        }*/


//    @RequestMapping(method = RequestMethod.GET)
//    public  ResponseEntity<?> getListStudentWithPaginate(
//            @RequestParam(name = "page", defaultValue = "1") int page,
//            @RequestParam(name = "limit", defaultValue = "10") int limit,
//            @RequestParam(name = "sort", required = false) String sort,
//            @RequestParam(name = "category_id", defaultValue = "-1") Long categoryId,
//            @RequestParam(name = "start", required = false) String start,
//            @RequestParam(name = "end", required = false) String end,
//            @RequestParam(name = "keyword", required = false) String keyword){
//        SearchBody searchBody = SearchBody.SearchBodyBuilder.aSearchBody()
//                .withPage(page)
//                .withLimit(limit)
//                .withKeyword(keyword)
//                .withCategoryId(categoryId)
//                .withSort(sort)
//                .withStart(start)
//                .withEnd(end)
//                .build();
//        return ResponseEntity.ok(productService.findAllAndFilter(searchBody));
//    }


/*
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> getList(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) String name
    ) {
        try {
            List<Product> products = new ArrayList<>();
            List<ProductDto> productDtos = new ArrayList<>();
            PageRequest paging = PageRequest.of(page - 1, size);
            Page<Product> pageTuts;
            if (name == null) {
                pageTuts = productRepository.findAll(paging);
            }
            else {
                pageTuts = productRepository.searchAllProduct(name, paging);
            }
            products = pageTuts.getContent();
            for (Product product : products
            ) {
                productDtos.add(new ProductDto(product));
            }
            Map<String, Object> response = new HashMap<>();
            response.put("product", productDtos);
            response.put("currentPage", pageTuts.getNumber() + 1);
            response.put("totalItems", pageTuts.getTotalElements());
            response.put("totalPages", pageTuts.getTotalPages());
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
*/


    @RequestMapping(method = RequestMethod.GET, path = "search")
    public ResponseEntity<List<ProductDto>> searchNameProduct(@RequestParam("name") String name) {
        System.out.println(name);
        return ResponseEntity.ok(productService.searchName(name));
    }

    @RequestMapping(method = RequestMethod.GET, path = "listProduct/{name}")
    public ResponseEntity<?> listProductName(@PathVariable String name) {
        Optional<List<ProductDto>> optionalProduct = Optional.ofNullable(productService.searchName(name));
        if (!optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Integer id) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalProduct.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Product> create(@RequestBody Product product) {
        Product product1 = productService.save(product);
        return ResponseEntity.ok(product1);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Product> update(@PathVariable Integer id, @RequestBody Product product) {
        Optional<Product> optionalProduct = productService.findById(id);
        if (!optionalProduct.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Product existProduct = optionalProduct.get();
        // map object
        existProduct.setCategory_id(product.getCategory_id());
        existProduct.setName(product.getName());
        existProduct.setPrice(product.getPrice());
        return ResponseEntity.ok(productService.save(existProduct));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        if (!productService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
