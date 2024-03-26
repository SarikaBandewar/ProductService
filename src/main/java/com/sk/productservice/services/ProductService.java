package com.sk.productservice.services;


import com.sk.productservice.dto.FakeStoreProductDto;
import com.sk.productservice.dto.ProductDto;
import com.sk.productservice.models.Category;
import com.sk.productservice.models.Product;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);

    List<Product> getAllProducts();

    List<Category> getAllCategories();

    List<Product> getProductsOfCategory(String category);

    Product createProduct(FakeStoreProductDto product);

    Product replaceProduct(Long id, ProductDto productDto);

}
