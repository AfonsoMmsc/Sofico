package com.sofico_backend.sofico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sofico_backend.sofico.models.ProductImage;

public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
}
