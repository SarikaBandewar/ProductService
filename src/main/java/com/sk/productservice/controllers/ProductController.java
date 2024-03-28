package com.sk.productservice.controllers;

import com.sk.productservice.dto.ProductDto;
import com.sk.productservice.models.Category;
import com.sk.productservice.models.Product;
import com.sk.productservice.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
        Product product = productService.getProductById(id);
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @GetMapping("/categories")
    public List<Category> getAllCategories() {
        return productService.getAllCategories();
    }

    @GetMapping("/category/{category}")
    public List<Product> getProductsOfCategory(@PathVariable("category") String category) {
        return productService.getProductsOfCategory(category);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductDto productDto) {
        return productService.createProduct(productDto);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return productService.updateProduct(id, productDto);
    }

    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        return productService.replaceProduct(id, productDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        Boolean response = productService.deleteProduct(id);
        if (response) return new ResponseEntity<>(HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
