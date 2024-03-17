package ru.ozerov.stepup.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.ozerov.stepup.dto.InstanceRequestDto;
import ru.ozerov.stepup.dto.InstanceResponseDataDto;
import ru.ozerov.stepup.dto.InstanceResponseDto;
import ru.ozerov.stepup.dto.RegisterStatus;
import ru.ozerov.stepup.entities.Agreement;
import ru.ozerov.stepup.entities.Product;
import ru.ozerov.stepup.entities.ProductRegister;
import ru.ozerov.stepup.exceptions.AgreementAlreadyExistsException;
import ru.ozerov.stepup.exceptions.ContractNumberAlreadyExistsException;
import ru.ozerov.stepup.exceptions.ProductCodeNotFoundException;
import ru.ozerov.stepup.exceptions.ProductNotFoundException;
import ru.ozerov.stepup.repositories.*;

import java.util.ArrayList;

@Service
@AllArgsConstructor
public class InstanceService {
    ProductRepository productRepository;
    AgreementRepository agreementRepository;
    RefProductClassRepository refProductClassRepository;
    AccountPoolRepository accountPoolRepository;
    ProductRegisterRepository productRegisterRepository;

    public InstanceResponseDto create(InstanceRequestDto request)
            throws ContractNumberAlreadyExistsException, ProductCodeNotFoundException, AgreementAlreadyExistsException, ProductNotFoundException {
        var instanceId = request.getInstanceId();
        var registerIdList = new ArrayList<Integer>();
        var agreementIdList = new ArrayList<Integer>();

        if (instanceId == null) { //процесс создания нового экземпляра
            //проверка ЭП на дубликаты
            if (productRepository.findByNumber(request.getContractNumber()).isPresent()) {
                throw new ContractNumberAlreadyExistsException(request.getContractNumber(),
                        productRepository.findByNumber(request.getContractNumber()).get().getId().toString());
            }
            //Проверка таблицы ДС (agreement) на дубликаты
            for (var agr : request.getAgreements()) {
                var agreement = agreementRepository.findByNumber(agr.getNumber());
                if (agreement.isPresent()) {
                    throw new AgreementAlreadyExistsException(agr.getNumber(),
                            agreement.get().getId().toString());
                }
            }
            //По КодуПродукта найти связные записи в Каталоге Типа регистра
            var refProductClass = refProductClassRepository.findByValue(request.getProductCode());
            if (refProductClass.isPresent()) {
                var listTypes = refProductClass.get()
                        .getRegisterTypes()
                        .stream()
                        .filter(x -> x.getAccountType().equals("Клиентский")).toList();
                if (listTypes.isEmpty()) {
                    throw new ProductCodeNotFoundException(request.getProductCode());
                }
                var product = new Product(
                        null,
                        refProductClass.get().getInternalId(),
                        Integer.parseInt(request.getMdmCode()),
                        request.getProductType(),
                        request.getContractNumber(),
                        request.getPriority(),
                        request.getContractDate().atStartOfDay(),
                        null,
                        null,
                        null,
                        request.getInterestRatePenalty(),
                        request.getMinimalBalance(),
                        request.getThresholdAmount(),
                        request.getAccountingDetails(),
                        request.getRateType(),
                        request.getTaxPercentageRate(),
                        null,
                        null
                );
                productRepository.save(product);
                instanceId = product.getId();

                var list = accountPoolRepository.findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
                        request.getBranchCode(), request.getIsoCurrencyCode(), request.getMdmCode(),
                        request.getPriority().toString(), product.getType());
                if (list.isPresent()) {
                    for (var account : list.get().getAccounts()) {
                        if (account == null) continue;
                        var register = new ProductRegister(
                                null,
                                instanceId,
                                product.getType(),
                                account.getId(),
                                request.getIsoCurrencyCode(),
                                String.valueOf(RegisterStatus.OPEN),
                                account.getAccountNumber()
                        );
                        productRegisterRepository.save(register);
                        registerIdList.add(register.getId());
                    }
                }
            }
        } else {
            var product = productRepository.findById(request.getInstanceId());
            if (product.isEmpty()) {
                throw new ProductNotFoundException(request.getInstanceId());
            }
            for (var agr : request.getAgreements()) {
                var agreement = agreementRepository.findByNumber(agr.getNumber());
                if (agreement.isPresent()) {
                    throw new AgreementAlreadyExistsException(agr.getNumber(),
                            agreement.get().getId().toString());
                }
            }
            for (var agr : request.getAgreements()) {
                var agreement = new Agreement(
                        null,
                        product.get().getId(),
                        agr.getGeneralAgreementId(),
                        agr.getSupplementaryAgreementId(),
                        agr.getArrangementType(),
                        agr.getShedulerJobId(),
                        agr.getNumber(),
                        agr.getOpeningDate().atStartOfDay(),
                        agr.getClosingDate().atStartOfDay(),
                        agr.getCancelDate().atStartOfDay(),
                        agr.getValidityDuration(),
                        agr.getCancellationReason(),
                        agr.getStatus(),
                        agr.getInterestCalculationDate().atStartOfDay(),
                        agr.getInterestRate(),
                        agr.getCoefficient(),
                        agr.getCoefficientAction(),
                        agr.getMinimumInterestRate(),
                        agr.getMinimumInterestRateCoefficient(),
                        agr.getMinimumInterestRateCoefficientAction(),
                        agr.getMaximalInterestRate(),
                        agr.getMaximalInterestRateCoefficient(),
                        agr.getMaximalInterestRateCoefficientAction()
                );
                agreementRepository.save(agreement);
                agreementIdList.add(agreement.getId());
            }
        }
        return new InstanceResponseDto(
                new InstanceResponseDataDto(
                        instanceId.toString(),
                        registerIdList,
                        agreementIdList
                )
        );
    }
}
