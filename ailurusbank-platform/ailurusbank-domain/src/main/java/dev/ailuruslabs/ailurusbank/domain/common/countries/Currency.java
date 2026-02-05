package dev.ailuruslabs.ailurusbank.domain.common.countries;

import java.util.Objects;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record Currency(
    String alphabeticCode,
    String numericCode,
    String name,
    int decimals,
    String symbol
) {

    public Currency {
        Objects.requireNonNull(alphabeticCode, "Alphabetic code cannot be null");
        Objects.requireNonNull(numericCode, "Numeric code cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(symbol, "Symbol cannot be null");

        failIf(alphabeticCode.isBlank(), "Alphabetic code cannot be blank");
        failIf(alphabeticCode.length() != 3, "Alphabetic code must have a length of 3");

        failIf(numericCode.isBlank(), "Numeric code cannot be blank");
        failIf(numericCode.length() != 3, "Numeric code must be 3 characters long");

        failIf(name.isBlank(), "Name cannot be blank");

        failIf(symbol.isBlank(), "Symbol cannot be blank");
        failIf(symbol.length() > 5, "Symbol cannot have a length greater than 5");


        failIf(decimals < 0, "Decimals cannot be less than 0");
    }

}
