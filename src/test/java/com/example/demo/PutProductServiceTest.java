package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.ProductRepository;
import com.product.exceptions.ProductNotFoundException;
import com.product.exceptions.ProductNotValidException;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.model.UpdateProductCommand;
import com.product.services.PutProductService;

public class PutProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private PutProductService putProductService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    public void given_update_product_is_invalid_when_put_product_service_throw_product_not_valid_exception() {
        assertThrows(ProductNotValidException.class, () -> putProductService.execute(new UpdateProductCommand(1, new Product())));
    }

    @Test
    public void given_update_product_is_valid_when_put_product_service_return_updated_product_dto() {
        Product product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("Product description with more than 4 characteres");
        product.setPrice(9.99);

        UpdateProductCommand updateProductCommand = new UpdateProductCommand(1, product);

        when(productRepository.findById(updateProductCommand.getId())).thenReturn(Optional.of(product));
        when(productRepository.save(product)).thenReturn(product);

        assertEquals(ResponseEntity.ok(new ProductDTO(product)), putProductService.execute(updateProductCommand));
    }

    @Test
    public void given_original_product_doesnt_exist_when_put_product_service_throw_product_not_found_exception() {
        Product product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("Product description with more than 4 characteres");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> putProductService.execute(new UpdateProductCommand(1, product)));
    }

}
