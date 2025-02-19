package com.product.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.Query;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.model.ProductRepository;

@Service
public class GetProductsService implements Query<Void, List<ProductDTO>>{

    private final ProductRepository productRepository;
    
    public GetProductsService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ResponseEntity<List<ProductDTO>> execute(Void input) {
        List<Product> products = productRepository.findAll();
        List<ProductDTO> productsDTO = products.stream().map(ProductDTO::new).toList();

        return ResponseEntity.status(HttpStatus.OK).body(productsDTO);
    }
}
