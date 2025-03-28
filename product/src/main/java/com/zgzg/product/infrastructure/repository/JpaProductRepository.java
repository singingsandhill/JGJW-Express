package com.zgzg.product.infrastructure.repository;

import com.zgzg.product.domain.model.Product;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface JpaProductRepository extends JpaRepository<Product, UUID> {
    @NotNull
    List<Product> findAllByDeletedAtIsNull();

    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE p.productName = :productName AND p.storeId = :storeId AND p.hubId = :hubId AND p.deletedAt IS NULL")
    boolean existsProduct(
            @Param("productName") String productName,
            @Param("storeId") UUID storeId,
            @Param("hubId") UUID hubId
    );


    @Query("SELECT p FROM Product p " +
            "WHERE p.deletedAt IS NULL " +
            "AND (p.productName LIKE %:name%)")
    Page<Product> searchProducts(@Param("name") String name, Pageable pageable);



}
