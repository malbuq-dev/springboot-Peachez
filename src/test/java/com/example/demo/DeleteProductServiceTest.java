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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ProductRepository;
import com.product.exceptions.ProductNotFoundException;
import com.product.model.Product;
import com.product.services.DeleteProductService;

public class DeleteProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private DeleteProductService deleteProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_id_exists_when_delete_product_service_return_void_response_entity() {
        when(productRepository.findById(1)).thenReturn(Optional.of(new Product()));

        assertEquals(ResponseEntity.status(HttpStatus.NO_CONTENT).build(), deleteProductService.execute(1));
    }

    @Test
    public void given_id_dont_exists_when_delete_product_service_return_product_not_found_exception() {
        when(productRepository.findById(1)).thenReturn(Optional.empty());
        assertThrows(ProductNotFoundException.class, () -> deleteProductService.execute(1));
    }
}
