package com.springboot.sentinel.enums;

import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum CoPatientType {
    DEPENDENTE_FORNECEDOR("Dependente do Fornecedor"),
    FUNCIONARIO("Funcionario"),
    DEPENDENTE_FUNCIONARIO("Dependente do Funcionario");

    @Getter(onMethod_ = { @JsonValue })
    private final String label;

    CoPatientType(String label) {
        this.label = label;
    }
}
