package ru.ozerov.stepup.exceptions;

import lombok.Getter;

@Getter
public class RegistryTypeCodeAlreadyExistsException extends Throwable {
    private final String registryTypeCode;
    private final String number;
    public RegistryTypeCodeAlreadyExistsException(String registryTypeCode, String number) {
        this.registryTypeCode = registryTypeCode;
        this.number = number;
    }
}
