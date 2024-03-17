package ru.ozerov.stepup.exceptions;

import lombok.Getter;

@Getter
public class RegistryTypeCodeNotExistsException extends Throwable {
    private final String registryTypeCode;
    public RegistryTypeCodeNotExistsException(String registryTypeCode) {
        this.registryTypeCode = registryTypeCode;
    }
}
