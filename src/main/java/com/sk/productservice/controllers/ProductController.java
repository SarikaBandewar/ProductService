package com.sk.productservice.controllers;

import com.sk.productservice.commons.AuthenticationCommons;
import com.sk.productservice.dto.ExceptionDto;
import com.sk.productservice.dto.ProductDto;
import com.sk.productservice.dto.Role;
import com.sk.productservice.dto.UserDto;
import com.sk.productservice.exceptions.InvalidInputData;
import com.sk.productservice.exceptions.ProductNotFoundException;
import com.sk.productservice.models.Category;
import com.sk.productservice.models.Product;
import com.sk.productservice.services.CategoryService;
import com.sk.productservice.services.ProductService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final AuthenticationCommons authenticationCommons;

    ProductController(@Qualifier("selfProductService") ProductService productService,
                      CategoryService categoryService, AuthenticationCommons authenticationCommons) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.authenticationCommons = authenticationCommons;
    }

    @GetMapping("/all/{token}")
    public ResponseEntity<List<Product>> getAllProducts(@PathVariable String token) {
        // validate the token using UserService
        UserDto userDto = authenticationCommons.validateToken(token);

        if(userDto == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

//        boolean isAdmin = false;
//        for(Role role : userDto.getRoles()) {
//            if (role.getName().equalsIgnoreCase("admin")) {
//                isAdmin = true;
//                break;
//            }
//        }

//        if (!isAdmin) return new ResponseEntity<>(HttpStatus.FORBIDDEN);

        return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) throws ProductNotFoundException {
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
    public ResponseEntity<Product> createProduct(@RequestBody ProductDto productDto) {
        Product product = convertProductDtoToProduct(productDto);
        try {
//            Product createdProduct = productService.createProduct(product);

            return new ResponseEntity<>(productService.createProduct(product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Product convertProductDtoToProduct(ProductDto productDto) {
        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());

        Optional<Category> categoryOptional = categoryService.getCategoryByTitle(product.getTitle());

        if (categoryOptional.isPresent()) {
            product.setCategory(categoryOptional.get());
        } else {
            Category category = new Category();
            category.setTitle(productDto.getCategory());
            product.setCategory(category);
        }
        return product;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        Product product = convertProductDtoToProduct(productDto);
        try {
            Product createdProduct = productService.updateProduct(id, product);
            return new ResponseEntity<>(createdProduct, HttpStatus.OK);
        } catch (InvalidInputData | ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> replaceProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        Product product = convertProductDtoToProduct(productDto);
        try {
            return new ResponseEntity<>(productService.replaceProduct(id, product), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (InvalidInputData | ProductNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteProduct(@PathVariable Long id) {
        try {
            Boolean response = productService.deleteProduct(id);
            if (response) return new ResponseEntity<>(HttpStatus.OK);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (ProductNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<ExceptionDto> handleProductControllerException() {
        ExceptionDto exceptionDto = new ExceptionDto();
        exceptionDto.setMessage("Product not found");
        return new ResponseEntity<>(exceptionDto, HttpStatus.NOT_FOUND);
    }
}
