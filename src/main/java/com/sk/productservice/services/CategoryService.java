package com.sk.productservice.services;

import com.sk.productservice.models.Category;
import com.sk.productservice.repositories.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CategoryService {
    final private CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public Optional<Category> getCategoryByTitle(String title) {
        return categoryRepository.findByTitle(title);
    }
}
