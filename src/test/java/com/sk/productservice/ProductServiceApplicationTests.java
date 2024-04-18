package com.sk.productservice;

import com.sk.productservice.repositories.ProductRepository;
import com.sk.productservice.repositories.projections.ProductWithIdTitle;
import com.sk.productservice.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
class ProductServiceApplicationTests {
	@Autowired
	private ProductRepository productRepository;

	@Test
	void contextLoads() {
	}











//
//	@Test
//	public void testQueries() {
//
//		Optional<ProductWithIdTitle> product = productRepository.findByProductId(2L);
//		if (product.isPresent()) {
//			System.out.println("id = " + product.get().getId());
//			System.out.println("title = " + product.get().getTitle());
//		} else {
//			System.out.println("id = null");
//		}
//	}


}
