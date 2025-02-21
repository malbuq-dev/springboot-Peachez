package com.product.services;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Command;
import com.ProductRepository;
import com.product.exceptions.ProductNotFoundException;
import com.product.model.Product;

@Service
public class DeleteProductService implements Command<Integer, Void>{

    private ProductRepository productRepository;

    public DeleteProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    @Override
    public ResponseEntity<Void> execute(Integer id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        
        if(optionalProduct.isPresent()) {
            productRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }

        throw new ProductNotFoundException();
    }
}
