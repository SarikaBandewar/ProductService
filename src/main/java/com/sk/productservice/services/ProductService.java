package com.sk.productservice.services;


import com.sk.productservice.dto.FakeStoreProductDto;
import com.sk.productservice.dto.ProductDto;
import com.sk.productservice.exceptions.InvalidInputData;
import com.sk.productservice.exceptions.ProductNotFoundException;
import com.sk.productservice.models.Category;
import com.sk.productservice.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id) throws ProductNotFoundException;

    List<Product> getAllProducts();

    List<Category> getAllCategories();

    List<Product> getProductsOfCategory(String category);

    Product createProduct(Product product);

    Product replaceProduct(Long id, Product product) throws InvalidInputData, ProductNotFoundException;

    Product updateProduct(Long id, Product product) throws ProductNotFoundException, InvalidInputData;

    Boolean deleteProduct(Long id) throws ProductNotFoundException;
}
