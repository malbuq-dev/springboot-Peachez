package com.product.validators;

import com.product.exceptions.ErrorMessages;
import com.product.exceptions.ProductNotValidException;
import com.product.model.Product;

import io.micrometer.common.util.StringUtils;

public class ProductValidator {
    private ProductValidator() {

    }

    public static void execute(Product product) {
        if(StringUtils.isEmpty(product.getName())) {
            throw new ProductNotValidException(ErrorMessages.NAME_REQUIRED.getMessage());
        }

        if(product.getDescription().length() < 4) {
            throw new ProductNotValidException(ErrorMessages.DESCRIPTION_LENGTH.getMessage());
        }

        if(product.getPrice() == null || product.getPrice() < 0.00) {
            throw new ProductNotValidException(ErrorMessages.PRICE_CANNOT_BE_NULL_OR_NEGATIVE.getMessage());
        }
    }
}
