package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.ProductRepository;
import com.product.exceptions.ProductNotValidException;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.services.CreateProductService;

public class CreateProductServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private CreateProductService createProductService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void should_create_product_and_return_product_dto_when_valid() {
        Product product = new Product();
        product.setId(1);
        product.setName("test");
        product.setDescription("Product description with more than 4 characteres");
        product.setPrice(9.99);

        when(productRepository.save(product)).thenReturn(product);
        
        ResponseEntity<ProductDTO> response = createProductService.execute(product);
        
        assertEquals(ResponseEntity.status(HttpStatus.CREATED).body(new ProductDTO(product)), response);

        verify(productRepository, times(1)).save(product);
    }

    @Test
    public void given_invalid_name_throw_product_not_valid_exception() {
        Product product = new Product();
        product.setId(1);
        product.setName("");
        product.setDescription("Product description with more than 4 characteres");
        product.setPrice(9.99);

        assertThrows(ProductNotValidException.class, () -> createProductService.execute(product));
    }

    @Test
    public void given_invalid_description_throw_product_not_valid_exception() {
        Product product = new Product();
        product.setId(1);
        product.setName("name");
        product.setDescription("Pr");
        product.setPrice(9.99);

        assertThrows(ProductNotValidException.class, () -> createProductService.execute(product));
    }

    @Test
    public void given_invalid_price_throw_product_not_valid_exception() {
        Product product = new Product();
        product.setId(1);
        product.setName("name");
        product.setDescription("Product");
        product.setPrice(-9.99);

        assertThrows(ProductNotValidException.class, () -> createProductService.execute(product));
    }

    
}
