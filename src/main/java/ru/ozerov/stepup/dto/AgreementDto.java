package ru.ozerov.stepup.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
public class AgreementDto {
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private String arrangementType;
    private Integer shedulerJobId;
    @NotEmpty
    private String Number;
    @NotNull
    private LocalDate openingDate;
    private LocalDate closingDate;
    private LocalDate cancelDate;
    private Integer validityDuration;
    private String cancellationReason;
    private String status;
    private LocalDate interestCalculationDate;
    private BigDecimal interestRate;
    private BigDecimal coefficient;
    private String coefficientAction;
    private BigDecimal minimumInterestRate;
    private BigDecimal minimumInterestRateCoefficient;
    private String minimumInterestRateCoefficientAction;
    private BigDecimal maximalInterestRate;
    private BigDecimal maximalInterestRateCoefficient;
    private String maximalInterestRateCoefficientAction;
}
