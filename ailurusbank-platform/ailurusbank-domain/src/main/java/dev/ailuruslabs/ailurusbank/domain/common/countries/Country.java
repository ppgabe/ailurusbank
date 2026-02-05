package dev.ailuruslabs.ailurusbank.domain.common.countries;

import java.util.Objects;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record Country(String alpha2, String alpha3, String name, String currencyCode) {
    public Country {
        Objects.requireNonNull(alpha2, "Alpha2 cannot be null");
        Objects.requireNonNull(alpha3, "Alpha3 cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(currencyCode, "Currency alpha2 cannot be null");

        failIf(alpha2.isBlank(), "Alpha2 cannot be blank");
        failIf(alpha2.length() != 2, "Alpha2 must have a length of 2");

        failIf(alpha3.isBlank(), "Alpha3 cannot be blank");
        failIf(alpha3.length() != 3, "Alpha3 must have a length of 3");

        failIf(name.isBlank(), "Name cannot be blank");

        failIf(currencyCode.isBlank(), "Currency code cannot be blank");
        failIf(currencyCode.length() != 3, "Currency code must have a length of 3");
    }
}