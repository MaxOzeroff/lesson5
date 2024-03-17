package ru.ozerov.stepup.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.NaturalId;

import java.util.List;

@Entity(name = "tpp_ref_product_class")
@Getter
public class RefProductClass {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internalId;
    @NaturalId
    @Column(nullable = false, unique = true)
    private String value;
    private String gbiCode;
    private String gbiName;
    private String productRowCode;
    private String productRowName;
    private String subclassCode;
    private String subclassName;

    @OneToMany(mappedBy = "refProductClass")
    private List<RefProductRegisterType> registerTypes;
}
