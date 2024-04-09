package com.sk.productservice.repositories;

import com.sk.productservice.models.Product;
import com.sk.productservice.repositories.projections.ProductWithIdTitle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("select p from Product p where p.category.Id = :categoryId")
    List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

    @Query(value = "select * from product where id = :productId", nativeQuery = true)
    Optional<ProductWithIdTitle> findByProductId(@Param("productId") Long productId);
}
