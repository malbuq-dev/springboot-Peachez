package com.product;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private final CreateProductService createProductService;
    private final GetProductService getProductService;
    private final PutProductService putProductService;
    private final DeleteProductService deleteProductService;

    public ProductController(
        CreateProductService createProductService, GetProductService getProductService, PutProductService putProductService, DeleteProductService deleteProductService) {
        this.createProductService = createProductService;
        this.getProductService = getProductService;
        this.putProductService = putProductService;
        this.deleteProductService = deleteProductService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct() {
        return createProductService.execute();
    }
    
    @GetMapping
    public ResponseEntity<String> getProduct() {
        return getProductService.execute();
    }
    
    @PutMapping
    public ResponseEntity<String> putProduct() {
        return putProductService.execute();
    }
    
    @DeleteMapping
    public ResponseEntity<String> deleteProduct() {
        return deleteProductService.execute();
    }
}
