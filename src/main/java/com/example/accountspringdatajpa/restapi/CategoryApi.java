package com.example.accountspringdatajpa.restapi;

import com.example.accountspringdatajpa.entity.Account;
import com.example.accountspringdatajpa.entity.Category;
import com.example.accountspringdatajpa.service.AccountService;
import com.example.accountspringdatajpa.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/v1/categories")
@CrossOrigin("http://localhost:8080")
public class CategoryApi {
    @Autowired
    CategoryService categoryService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Category>> getList() {
        return ResponseEntity.ok(categoryService.findAll());
    }

    @RequestMapping(method = RequestMethod.GET, path = "{id}")
    public ResponseEntity<?> getDetail(@PathVariable Long id) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(optionalCategory.get());
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Category> create(@RequestBody Category category) {
        Category c = categoryService.save(category);
        return ResponseEntity.ok(c);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "{id}")
    public ResponseEntity<Category> update(@PathVariable Long id, @RequestBody Category category) {
        Optional<Category> optionalCategory = categoryService.findById(id);
        if (!optionalCategory.isPresent()) {
            ResponseEntity.badRequest().build();
        }
        Category existCategory = optionalCategory.get();
        // map object
        existCategory.setTitle(category.getTitle());
        return ResponseEntity.ok(categoryService.save(existCategory));
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        if (!categoryService.findById(id).isPresent()) {
            ResponseEntity.badRequest().build();
        }
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
