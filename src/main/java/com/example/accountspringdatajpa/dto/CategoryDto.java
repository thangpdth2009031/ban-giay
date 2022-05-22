package com.example.accountspringdatajpa.dto;

import com.example.accountspringdatajpa.entity.Category;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long id;
    private String title;

    public CategoryDto(Category category) {
        id = category.getId();
        title = category.getTitle();
    }
}
