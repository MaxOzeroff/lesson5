package ru.ozerov.stepup.exceptions;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends Throwable {
    private final Integer instanceId;

    public ProductNotFoundException(Integer instanceId) {
        this.instanceId = instanceId;
    }
}
