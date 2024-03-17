package ru.ozerov.stepup.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import ru.ozerov.stepup.dto.AccountRequestDto;
import ru.ozerov.stepup.dto.AccountResponseDataDto;
import ru.ozerov.stepup.dto.AccountResponseDto;
import ru.ozerov.stepup.dto.RegisterStatus;
import ru.ozerov.stepup.entities.ProductRegister;
import ru.ozerov.stepup.exceptions.RegistryTypeCodeAlreadyExistsException;
import ru.ozerov.stepup.exceptions.RegistryTypeCodeNotExistsException;
import ru.ozerov.stepup.repositories.AccountPoolRepository;
import ru.ozerov.stepup.repositories.ProductRegisterRepository;
import ru.ozerov.stepup.repositories.RefProductRegisterTypeRepository;

@Service
@AllArgsConstructor
public class RegisterService {
    private final ProductRegisterRepository productRegisterRepository;
    private final RefProductRegisterTypeRepository refProductRegisterTypeRepository;
    private final AccountPoolRepository accountPoolRepository;

    public AccountResponseDto create(AccountRequestDto request) throws RegistryTypeCodeAlreadyExistsException, RegistryTypeCodeNotExistsException {
        //Проверка таблицы ПР (таблица tpp_product_register) на дубликаты
        if (productRegisterRepository.findByIdAndType(request.getInstanceId(), request.getRegistryTypeCode()).isPresent()) {
            throw new RegistryTypeCodeAlreadyExistsException(request.getRegistryTypeCode(),
                    request.getInstanceId().toString());
        }
        //Взять значение из Request.Body.registryTypeCode и найти соответствующие ему записи в tpp_ref_product_register_type.value
        var productRegisterType = refProductRegisterTypeRepository.findByValue(request.getRegistryTypeCode());
        if (productRegisterType.isEmpty()) {
            throw new RegistryTypeCodeNotExistsException(request.getRegistryTypeCode());
        }
        var list = accountPoolRepository.findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(
                request.getBranchCode(),
                request.getCurrencyCode(),
                request.getMdmCode(),
                request.getPriorityCode(),
                request.getRegistryTypeCode()
        );
        if (list.isPresent()) {
            var account = CollectionUtils.firstElement(list.get().getAccounts());
            if (account == null) return null;

            productRegisterRepository.save(
                    new ProductRegister(
                            null,
                            request.getInstanceId(),
                            productRegisterType.get().getValue(),
                            account.getId(),
                            request.getCurrencyCode(),
                            String.valueOf(RegisterStatus.OPEN),
                            account.getAccountNumber()
                    )
            );

            return new AccountResponseDto(
                    new AccountResponseDataDto(
                            account.getAccountNumber()
                    )
            );
        }
        return null;
    }
}
