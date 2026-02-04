package dev.ailuruslabs.ailurusbank.domain.common.countries;

import java.util.Objects;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record Currency(
    String code,
    String numCode,
    String name,
    int decimals,
    String symbol
) {

    public Currency {
        Objects.requireNonNull(code, "Code cannot be null");
        Objects.requireNonNull(numCode, "Num code cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(symbol, "Symbol cannot be null");

        failIf(code.isBlank(), "Code cannot be blank");
        failIf(code.length() != 3, "Code must have a length of 3");

        failIf(numCode.isBlank(), "Num code cannot be blank");
        failIf(numCode.length() != 3, "Num code must be 3 characters long");

        failIf(name.isBlank(), "Name cannot be blank");

        failIf(symbol.isBlank(), "Symbol cannot be blank");
        failIf(symbol.length() > 5, "Symbol cannot have a length greater than 5");


        failIf(decimals < 0, "Decimals cannot be less than 0");
    }

}
