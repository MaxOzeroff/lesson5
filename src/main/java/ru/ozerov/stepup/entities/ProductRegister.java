package ru.ozerov.stepup.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity(name = "tpp_product_register")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductRegister {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer productId;
    private String type;
    private Integer account;
    private String currencyCode;
    private String state;
    private String accountNumber;
}
