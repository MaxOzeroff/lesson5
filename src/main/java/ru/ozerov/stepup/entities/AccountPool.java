package ru.ozerov.stepup.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;

@Entity(name = "account_pool")
@Getter
public class AccountPool {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String branchCode;
    private String currencyCode;
    private String mdmCode;
    private String priorityCode;
    private String registryTypeCode;

    @OneToMany(mappedBy = "accountPool")
    private List<Account> accounts;
}
