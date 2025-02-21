package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import com.ProductRepository;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.services.GetProductsService;

public class GetProductsServiceTest {
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private GetProductsService getProductsService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_at_least_one_product_exists_when_get_products_service_return_product_dto_list() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product());

        when(productRepository.findAll()).thenReturn(productList);

        List<ProductDTO> productsDTO = productList.stream().map(ProductDTO::new).toList();

        assertEquals(ResponseEntity.status(HttpStatus.OK).body(productsDTO), getProductsService.execute(null));
    }

    @Test
    public void given_no_product_exists_when_get_products_service_return_empty_list() {
        List<Product> productList = new ArrayList<>();

        when(productRepository.findAll()).thenReturn(productList);

        assertEquals(ResponseEntity.status(HttpStatus.OK).body(productList), getProductsService.execute(null));
    }
}
