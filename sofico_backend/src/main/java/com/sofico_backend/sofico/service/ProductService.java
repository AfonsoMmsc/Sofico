package com.sofico_backend.sofico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sofico_backend.sofico.models.Product;
import com.sofico_backend.sofico.models.ProductImage;
import com.sofico_backend.sofico.repository.ProductImageRepository;
import com.sofico_backend.sofico.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductImageRepository productImageRepository;

    public ProductService(ProductRepository productRepository, ProductImageRepository productImageRepository) {
        this.productRepository = productRepository;
        this.productImageRepository = productImageRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public Product addProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public ProductImage addProductImage(Long productId, ProductImage image) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        image.setProduct(product);
        return productImageRepository.save(image);
    }

    public void deleteProductImage(Long imageId) {
        productImageRepository.deleteById(imageId);
    }
}
