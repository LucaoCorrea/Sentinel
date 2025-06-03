package com.springboot.sentinel.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CPFValidator implements ConstraintValidator<CPF, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.length() != 11 || value.matches("(\\d)\\1{10}"))
            return false;

        // Remover qualquer caractere não numérico
        String cleanCpf = value.replaceAll("[^0-9]", "");

        if (cleanCpf.length() != 11) {
            return false;
        }

        try {
            int sum = 0;
            for (int i = 0; i < 9; i++)
                sum += (cleanCpf.charAt(i) - '0') * (10 - i);
            int firstDigit = (sum * 10) % 11;
            if (firstDigit == 10)
                firstDigit = 0;
            if (firstDigit != (cleanCpf.charAt(9) - '0'))
                return false;

            sum = 0;
            for (int i = 0; i < 10; i++)
                sum += (cleanCpf.charAt(i) - '0') * (11 - i);
            int secondDigit = (sum * 10) % 11;
            if (secondDigit == 10)
                secondDigit = 0;
            return secondDigit == (cleanCpf.charAt(10) - '0');
        } catch (Exception e) {
            return false;
        }
    }
}
