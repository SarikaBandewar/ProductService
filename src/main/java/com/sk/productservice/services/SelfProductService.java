package com.sk.productservice.services;

import com.sk.productservice.dto.ProductDto;
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
    public Product createProduct(ProductDto productDto) {
        Optional<Category> optionalCategory = categoryRepository.findByTitle(productDto.getCategory());
        Product product = convertProductDtoToProduct(productDto);
        if (optionalCategory.isEmpty()) {
            Category category = categoryRepository.save(product.getCategory());
            product.setCategory(category);
        } else {
            product.setCategory(optionalCategory.get());
        }
        return productRepository.save(product);
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {
        return null;
    }

    @Override
    public Product updateProduct(Long id, ProductDto productDto) throws ProductNotFoundException, InvalidInputData {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if (optionalProduct.isEmpty()) {
            throw new ProductNotFoundException();
        }
        if (productDto == null) throw new InvalidInputData();

        Product currentProduct = optionalProduct.get();
        if (productDto.getTitle() != null) {
            currentProduct.setTitle(productDto.getTitle());
        }
        if(productDto.getPrice() != null)
            currentProduct.setPrice(productDto.getPrice());
        if(productDto.getDescription() != null)
            currentProduct.setDescription(productDto.getDescription());
        if(productDto.getCategory() != null) {
            if(!currentProduct.getCategory().getTitle().equals(productDto.getCategory())) {
                Optional<Category> optionalCategory = categoryRepository.findByTitle(productDto.getCategory());
                if (optionalCategory.isEmpty()) {
                    Category category = categoryRepository.save(currentProduct.getCategory());
                    currentProduct.setCategory(category);
                }
            }
        }
        if(productDto.getImage() != null)
            currentProduct.setImage(productDto.getImage());

        return productRepository.save(currentProduct);
    }

    @Override
    public Boolean deleteProduct(Long id) {
        return null;
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {
        return getProduct(productDto);
    }

    static Product getProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setCategory(category);
        return product;
    }
}
