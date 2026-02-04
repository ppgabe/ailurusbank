package dev.ailuruslabs.ailurusbank.domain.common.countries;

import java.util.Objects;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record Country(String code, String iso3, String name, String currencyCode) {
    public Country {
        Objects.requireNonNull(code, "Code cannot be null");
        Objects.requireNonNull(iso3, "ISO3 cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(currencyCode, "Currency code cannot be null");

        failIf(code.isBlank(), "Code cannot be blank");
        failIf(code.length() != 2, "Code must have a length of 2");

        failIf(iso3.isBlank(), "ISO3 cannot be blank");
        failIf(iso3.length() != 3, "ISO3 must have a length of 3");

        failIf(name.isBlank(), "Name cannot be blank");

        failIf(currencyCode.isBlank(), "Currency code cannot be blank");
        failIf(currencyCode.length() != 3, "Currency code must have a length of 3");
    }
}