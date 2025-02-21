package com.product.services;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Command;
import com.ProductRepository;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.validators.ProductValidator;

@Service
public class CreateProductService implements Command<Product, ProductDTO> {

    private ProductRepository productRepository;

    public CreateProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Product product) {

        ProductValidator.execute(product);

        Product savedProduct = productRepository.save(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(savedProduct));
    }
}
