package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import com.ProductRepository;
import com.product.model.Product;
import com.product.model.ProductDTO;
import com.product.services.SearchProductService;

public class SearchProductServiceTest { 
    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private SearchProductService searchProductService;
    
    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void given_there_are_products_found_when_search_product_service_return_product_dto_list() {
        List<Product> products = new ArrayList<Product>();
        products.add(new Product());

        List<ProductDTO> productsDTO = products.stream().map(product -> new ProductDTO(product)).toList();

        when(productRepository.findByNameOrDescriptionContaining(new String())).thenReturn(products);
        assertEquals(ResponseEntity.ok(productsDTO), searchProductService.execute(new String()));
    }

    @Test
    public void given_there_are_no_products_found_when_search_product_service_return_empty_list() {
        List<Product> products = new ArrayList<Product>();

        List<ProductDTO> productsDTO = products.stream().map(product -> new ProductDTO(product)).toList();

        when(productRepository.findByNameOrDescriptionContaining(new String())).thenReturn(products);
        assertEquals(ResponseEntity.ok(productsDTO), searchProductService.execute(new String()));
    }
}
