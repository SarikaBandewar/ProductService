package com.sk.productservice.controllers;

import com.sk.productservice.exceptions.ProductNotFoundException;
import com.sk.productservice.models.Category;
import com.sk.productservice.models.Product;
import com.sk.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {

    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    @Test
    void getProductById() throws ProductNotFoundException {

        Product product = new Product();
        product.setId(10L);
        product.setTitle("ajhak");
        product.setDescription("ajhak");
        Category category = new Category();
        category.setId(1L);
        category.setTitle("Category1");
        product.setCategory(category);

        when(productService.getProductById(10L)).thenReturn(product);

        ResponseEntity<Product> expectedResponse = new ResponseEntity<>(product, HttpStatus.OK);

        ResponseEntity<Product> actualResponse = productController.getProductById(10L);

        assertEquals(expectedResponse.getBody(), actualResponse.getBody());
        assertEquals(expectedResponse.getStatusCode(), actualResponse.getStatusCode());
    }
}