package ru.ozerov.stepup.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import ru.ozerov.stepup.entities.Agreement;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@Getter
public class InstanceRequestDto {
    private Integer instanceId;
    @NotBlank
    private String productType;
    @NotBlank
    private String productCode;
    @NotBlank
    private String registerType;
    @NotBlank
    private String mdmCode;
    @NotBlank
    private String contractNumber;
    @NotNull
    private LocalDate contractDate;
    @NotNull
    private Integer priority;
    private BigDecimal interestRatePenalty;
    private BigDecimal minimalBalance;
    private BigDecimal thresholdAmount;
    private String accountingDetails;
    private String rateType;
    private BigDecimal taxPercentageRate;
    private BigDecimal technicalOverdraftLimitAmount;
    @NotNull
    private Integer contractId;
    @NotBlank
    private String BranchCode;
    @NotBlank
    private String isoCurrencyCode;
    @NotBlank
    private String urgencyCode;
    private Integer ReferenceCode;

    private List<AdditionalPropertiesVip> additionalPropertiesVip;
    @Valid
    private List<AgreementDto> agreements;
}
