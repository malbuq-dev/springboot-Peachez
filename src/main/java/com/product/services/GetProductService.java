package com.product.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ProductRepository;
import com.Query;
import com.product.exceptions.ProductNotFoundException;
import com.product.model.Product;
import com.product.model.ProductDTO;

@Service
public class GetProductService implements Query<Integer, ProductDTO>{

    private final ProductRepository productRepository;

    private static final Logger logger = LoggerFactory.getLogger(GetProductService.class);
    
    public GetProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<ProductDTO> execute(Integer id) {
        Optional<Product> productOptional = productRepository.findById(id);

        logger.info("Executing " + getClass() + "input " + id);

        if(productOptional.isPresent()) {
            return ResponseEntity.ok(new ProductDTO(productOptional.get()));
        }

        throw new ProductNotFoundException();
    }
}
