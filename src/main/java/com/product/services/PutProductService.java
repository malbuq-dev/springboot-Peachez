package com.product.services;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Command;
import com.product.exceptions.ProductNotFoundException;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.model.ProductRepository;
import com.product.model.UpdateProductCommand;

@Service
public class PutProductService implements Command<UpdateProductCommand, ProductDTO>{  
    private ProductRepository productRepository;

    public PutProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public ResponseEntity<ProductDTO> execute(UpdateProductCommand updateProductCommand) {
        Optional<Product> originalProductOptional = productRepository.findById(updateProductCommand.getId());

        if(originalProductOptional.isPresent()) {
            Product newProduct = updateProductCommand.getProduct();
            newProduct.setId(updateProductCommand.getId());

            productRepository.save(newProduct);
            return ResponseEntity.ok(new ProductDTO(newProduct));
        }

        throw new ProductNotFoundException();
    }
}
