package ru.ozerov.stepup.exceptions;

import lombok.Getter;

@Getter
public class ContractNumberAlreadyExistsException extends Throwable {
    private final String contractNumber;
    private final String number;

    public ContractNumberAlreadyExistsException(String contractNumber, String number) {
        this.contractNumber = contractNumber;
        this.number = number;
    }
}
