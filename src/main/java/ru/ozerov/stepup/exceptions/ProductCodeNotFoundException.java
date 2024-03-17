package ru.ozerov.stepup.exceptions;

import lombok.Getter;

@Getter
public class ProductCodeNotFoundException extends Throwable {
    private final String productCode;

    public ProductCodeNotFoundException(String productCode) {
        this.productCode = productCode;
    }
}
