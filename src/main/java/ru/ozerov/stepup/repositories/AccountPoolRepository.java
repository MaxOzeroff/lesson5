package ru.ozerov.stepup.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.ozerov.stepup.entities.AccountPool;

import java.util.Optional;

public interface AccountPoolRepository extends JpaRepository<AccountPool, Integer> {
    Optional<AccountPool> findByBranchCodeAndCurrencyCodeAndMdmCodeAndPriorityCodeAndRegistryTypeCode(String branch, String currency, String mdm, String priority, String registryType);
}
