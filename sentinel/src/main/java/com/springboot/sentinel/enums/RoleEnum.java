package com.springboot.sentinel.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;

public enum RoleEnum {
    ADMIN("Admin"),
    USER("User");

    @Getter(onMethod_ = {@JsonValue})
    private final String label;

    RoleEnum(String label) {
        this.label = label;
    }

    @JsonCreator
    public static RoleEnum fromValue(String value) {
        for (RoleEnum role : RoleEnum.values()) {
            if (role.name().equalsIgnoreCase(value) || role.label.equalsIgnoreCase(value)) {
                return role;
            }
        }
        throw new IllegalArgumentException("Unknown role: " + value);
    }
}
