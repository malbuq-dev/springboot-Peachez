package com.product.services;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ProductRepository;
import com.Query;
import com.product.model.ProductDTO;

@Service
public class SearchProductService implements Query<String, List<ProductDTO>> {

    private ProductRepository productRepository;

    public SearchProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(String input) {
        return ResponseEntity.ok(productRepository.findByNameOrDescriptionContaining(input).stream().map(ProductDTO::new).toList());
    }
    
}
