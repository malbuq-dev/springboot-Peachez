package com.product.services;

import java.util.Optional;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Query;
import com.product.exceptions.ProductNotFoundException;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.model.ProductRepository;

@Service
public class GetProductService implements Query<Integer, ProductDTO>{

    private final ProductRepository productRepository;
    
    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);

        if(productOptional.isPresent()) {
            return ResponseEntity.ok(new ProductDTO(productOptional.get()));
        }

        throw new ProductNotFoundException();
    }
}
