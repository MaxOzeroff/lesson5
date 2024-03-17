package ru.ozerov.stepup.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class InstanceResponseDataDto {
    private String instanceId;
    private List<Integer> registerId;
    private List<Integer> supplementaryAgreementId;
}
