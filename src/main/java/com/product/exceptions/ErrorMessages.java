package com.product.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product Not Found"),
    NAME_REQUIRED("Name is required"),
    DESCRIPTION_LENGTH("Description should be of length 4 or above"),
    PRICE_CANNOT_BE_NULL_OR_NEGATIVE("Price can't be null or negative");

    private String message;

    ErrorMessages(String message) {
            this.message = message;
    }

    public String getMessage() {
        return message;
    }

    
}
