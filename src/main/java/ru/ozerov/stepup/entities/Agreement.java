package ru.ozerov.stepup.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "agreement")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Agreement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private String generalAgreementId;
    private String supplementaryAgreementId;
    private String arrangementType;
    private Integer shedulerJobId;
    private String number;
    private LocalDateTime openingDate;
    private LocalDateTime closingDate;
    private LocalDateTime cancelDate;
    private Integer validityDuration;
    private String cancellationReason;
    private String status;
    private LocalDateTime interestCalculationDate;
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
