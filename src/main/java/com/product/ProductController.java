package com.product;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.model.UpdateProductCommand;
import com.product.services.CreateProductService;
import com.product.services.DeleteProductService;
import com.product.services.GetProductService;
import com.product.services.GetProductsService;
import com.product.services.PutProductService;

@RestController
public class ProductController {
    private final CreateProductService createProductService;
    private final GetProductsService getProductsService;
    private final GetProductService getProductService;
    private final PutProductService putProductService;
    private final DeleteProductService deleteProductService;

    public ProductController(
        CreateProductService createProductService, GetProductsService getProductsService, GetProductService getProductService, PutProductService putProductService, DeleteProductService deleteProductService) {
        this.createProductService = createProductService;
        this.getProductsService = getProductsService;
        this.getProductService = getProductService;
        this.putProductService = putProductService;
        this.deleteProductService = deleteProductService;
    }

    @PostMapping("/product")
    public ResponseEntity<ProductDTO> createProduct(@RequestBody Product product) {
        return createProductService.execute(product);
    }
    
    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getProducts() {
        return getProductsService.execute(null);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Integer id) {
        return getProductService.execute(id);
    }
    
    @PutMapping("/product/{id}")
    public ResponseEntity<ProductDTO> putProduct(@PathVariable Integer id, @RequestBody Product product) {
        return putProductService.execute(new UpdateProductCommand(id, product));
    }
    
    @DeleteMapping("/product/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Integer id) {
        return deleteProductService.execute(id);
    }
}
