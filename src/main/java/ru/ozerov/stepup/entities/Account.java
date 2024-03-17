package ru.ozerov.stepup.entities;

import jakarta.persistence.*;
import lombok.Getter;

@Entity(name = "account")
@Getter
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String accountNumber;
    boolean bussy;

    @ManyToOne
    @JoinColumn(name = "accountPoolId")
    private AccountPool accountPool;
}
