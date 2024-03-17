package ru.ozerov.stepup.entities;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity(name = "tpp_ref_product_register_type")
@Getter
public class RefProductRegisterType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internalId;
    private String value;
    private String registerTypeName;
    private LocalDateTime registerTypeStartDate;
    private LocalDateTime registerTypeEndDate;
    private String accountType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "productClassCode", referencedColumnName = "value")
    private RefProductClass refProductClass;
}
