package com.headers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.product.model.Product;

@RestController
public class HeaderController {

    @GetMapping("/header")
    public String getRegionalResponse(@RequestHeader(required = false, defaultValue = "US") String region) {
        // Normally, just like we've learned on previous videos, here we'd abstract it out with a service class,
        //but for the sake of simplicity, we're doing everything here for now

        if(region.equals("US")) return "BALD EAGLE";

        if(region.equals("CAN")) return "MAPLE SYRUP";

        return "COUNTRY NOT SUPPORTED";
    }

    @GetMapping(value = "/header/product", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Product> getProduct() {
        Product product = new Product();
        product.setId(1);
        product.setName("great product");
        product.setDescription("Product description with more than 4 characteres");
        product.setPrice(9.99);

        return ResponseEntity.ok(product);
    }
}
