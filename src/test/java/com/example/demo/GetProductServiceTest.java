package com.example.demo;
import com.ProductRepository;
import com.product.exceptions.ProductNotFoundException;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.services.GetProductService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

public class GetProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductService getProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_product_exists_when_get_product_service_return_dto() {
        Product product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("Product description with more than 4 characteres");
        product.setPrice(9.99);

        when(productRepository.findById(1)).thenReturn(Optional.of(product));
        
        ResponseEntity<ProductDTO> response = getProductService.execute(1);

        assertEquals(ResponseEntity.ok(new ProductDTO(product)), response);

        verify(productRepository, times(1)).findById(1);
    }

    @Test
    public void given_product_does_not_exist_when_get_product_service_throw_product_not_found_exception(){

        when(productRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> getProductService.execute(1));

        
    }
}
