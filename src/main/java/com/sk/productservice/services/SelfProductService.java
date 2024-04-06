package com.sk.productservice.services;

import com.sk.productservice.exceptions.InvalidInputData;
import com.sk.productservice.exceptions.ProductNotFoundException;
import com.sk.productservice.models.Category;
import com.sk.productservice.models.Product;
import com.sk.productservice.repositories.CategoryRepository;
import com.sk.productservice.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("selfProductService")
public class SelfProductService implements ProductService {

    final private ProductRepository productRepository;
    final private CategoryRepository categoryRepository;

    public SelfProductService(ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Product getProductById(Long id) throws ProductNotFoundException {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }

        return optionalProduct.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public List<Category> getAllCategories() {
        return List.of();
    }

    @Override
    public List<Product> getProductsOfCategory(String category) {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        Optional<Category> optionalCategory = categoryRepository.findByTitle(product.getCategory().getTitle());
        if (optionalCategory.isEmpty()) {
            Category category = categoryRepository.save(product.getCategory());
            product.setCategory(category);
        } else {
            product.setCategory(optionalCategory.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, Product product) throws ProductNotFoundException, InvalidInputData {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }
        if (product == null) throw new InvalidInputData();

        Product currentProduct = optionalProduct.get();
        if (product.getTitle() != null) {
            currentProduct.setTitle(product.getTitle());
        }
        if(product.getPrice() != null)
            currentProduct.setPrice(product.getPrice());
        if(product.getDescription() != null)
            currentProduct.setDescription(product.getDescription());
        if(product.getCategory() != null) {
            if(!currentProduct.getCategory().getTitle().equals(product.getCategory().getTitle())) {
                Optional<Category> optionalCategory = categoryRepository.findByTitle(product.getCategory().getTitle());
                if (optionalCategory.isEmpty()) {
                    Category category = categoryRepository.save(currentProduct.getCategory());
                    currentProduct.setCategory(category);
                }
            }
        }
        if(product.getImage() != null)
            currentProduct.setImage(product.getImage());

        return productRepository.save(currentProduct);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        return null;
    }
}
