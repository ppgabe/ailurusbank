package dev.ailuruslabs.ailurusbank.domain.clients.identifiers;

import java.util.Objects;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record IssuingAuthority(
    String code,
    String name,
    String countryCode,
    String type
) {

    public IssuingAuthority {
        Objects.requireNonNull(code, "Code cannot be null");
        failIf(code.isBlank(), "Code cannot be blank");
        failIf(code.length() > 20, "Code cannot have a length greater than 20");

        Objects.requireNonNull(name, "Name cannot be null");

        Objects.requireNonNull(countryCode, "Country code cannot be null");
        failIf(code.isBlank(), "Country code cannot be blank");
        failIf(countryCode.length() != 2, "Country code must have a length of 2");

        Objects.requireNonNull(type, "Type cannot be null");
        failIf(type.isBlank(), "Type cannot be blank");
    }
}
