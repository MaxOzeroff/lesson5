package ru.ozerov.stepup.exceptions;

import lombok.Getter;

@Getter
public class AgreementAlreadyExistsException extends Throwable {
    private final String contractNumber;
    private final String number;
    public AgreementAlreadyExistsException(String contractNumber, String number) {
        this.contractNumber = contractNumber;
        this.number = number;
    }
}
