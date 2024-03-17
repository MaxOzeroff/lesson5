package ru.ozerov.stepup.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity(name = "tpp_ref_account_type")
@AllArgsConstructor
@NoArgsConstructor
public class RefAccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer internalId;
    private String value;
}
