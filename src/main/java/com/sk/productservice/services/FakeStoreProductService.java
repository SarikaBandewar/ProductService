package com.sk.productservice.services;

import com.sk.productservice.dto.FakeStoreProductDto;
import com.sk.productservice.dto.ProductDto;
import com.sk.productservice.models.Category;
import com.sk.productservice.models.Product;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpMessageConverterExtractor;
import org.springframework.web.client.RequestCallback;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FakeStoreProductService implements ProductService{

    private final RestTemplate restTemplate;

    FakeStoreProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getProductById(Long id) {
        FakeStoreProductDto fakeStoreProductDto = restTemplate.getForObject("https://fakestoreapi.com/products/"+id, FakeStoreProductDto.class);
        if (fakeStoreProductDto == null)
            return null;
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    private Product convertFakeStoreProductDtoToProduct(FakeStoreProductDto fakeStoreProductDto) {
        Product product = new Product();
        product.setId(fakeStoreProductDto.getId());
        product.setPrice(fakeStoreProductDto.getPrice());
        product.setTitle(fakeStoreProductDto.getTitle());
        product.setImage(fakeStoreProductDto.getImage());
        product.setDescription(fakeStoreProductDto.getDescription());

        Category category = new Category();
        category.setTitle(fakeStoreProductDto.getCategory());
        product.setCategory(category);
        return product;
    }

    @Override
    public List<Product> getAllProducts() {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoList = restTemplate.getForEntity("https://fakestoreapi.com/products", FakeStoreProductDto[].class);

        return getProducts(fakeStoreProductDtoList);
    }

    @Override
    public List<Category> getAllCategories() {
        ResponseEntity<String[]> fakeStoreCategoryList = restTemplate.getForEntity("https://fakestoreapi.com/products/categories", String[].class);

        if(fakeStoreCategoryList.getBody() == null)
            return new ArrayList<>();

        List<Category> categoryList = new ArrayList<>();
        for (String sCategory : fakeStoreCategoryList.getBody()) {
            Category cat = new Category();
            cat.setTitle(sCategory);
            categoryList.add(cat);
        }
        return categoryList;
    }

    @Override
    public List<Product> getProductsOfCategory(String category) {
        ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoList = restTemplate.getForEntity("https://fakestoreapi.com/products/category/" + category, FakeStoreProductDto[].class);

        return getProducts(fakeStoreProductDtoList);
    }

    @Override
    public Product createProduct(FakeStoreProductDto product) {

        FakeStoreProductDto fakeStoreProductDto = restTemplate.postForObject("https://fakestoreapi.com/products", product, FakeStoreProductDto.class);

        if(fakeStoreProductDto == null)
            return null;

        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    @Override
    public Product replaceProduct(Long id, ProductDto productDto) {

        Product product = new Product();
        product.setTitle(productDto.getTitle());
        product.setPrice(productDto.getPrice());
        product.setImage(productDto.getImage());
        product.setDescription(productDto.getDescription());
        Category category = new Category();
        category.setTitle(productDto.getCategory());
        product.setCategory(category);

        RequestCallback requestCallback = restTemplate.httpEntityCallback(product, FakeStoreProductDto.class);
        HttpMessageConverterExtractor<FakeStoreProductDto> responseExtractor = new HttpMessageConverterExtractor(FakeStoreProductDto.class, restTemplate.getMessageConverters());
        FakeStoreProductDto fakeStoreProductDto =  restTemplate.execute("https://fakestoreapi.com/products/"+id, HttpMethod.PUT, requestCallback, responseExtractor);
        return convertFakeStoreProductDtoToProduct(fakeStoreProductDto);
    }

    private List<Product> getProducts(ResponseEntity<FakeStoreProductDto[]> fakeStoreProductDtoList) {
        if (fakeStoreProductDtoList.getBody() == null)
            return new ArrayList<>();

        List<Product> productList = new ArrayList<>();
        for (FakeStoreProductDto fakeStoreProductDto : fakeStoreProductDtoList.getBody()) {
            productList.add(convertFakeStoreProductDtoToProduct(fakeStoreProductDto));
        }
        return productList;
    }
}
