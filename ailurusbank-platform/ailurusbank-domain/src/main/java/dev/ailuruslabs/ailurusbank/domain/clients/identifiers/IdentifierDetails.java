package dev.ailuruslabs.ailurusbank.domain.clients.identifiers;

import java.util.Objects;
import java.util.UUID;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record IdentifierDetails(
    String type,
    String value,
    String issuingAuthority,
    String countryOfJurisdiction) {
    public IdentifierDetails {

        Objects.requireNonNull(type, "Type cannot be null");
        failIf(type.isBlank(), "Type cannot be blank");

        Objects.requireNonNull(value, "Value cannot be null");
        failIf(value.isBlank(), "Value cannot be blank");

        Objects.requireNonNull(issuingAuthority, "Issuing authority cannot be null");
        failIf(issuingAuthority.isBlank(), "Issuing authority cannot be blank");
        failIf(issuingAuthority.length() > 20,
            "Issuing authority cannot have a length greater than 20");

        Objects.requireNonNull(countryOfJurisdiction, "Country of jurisdiction cannot be null");
        failIf(countryOfJurisdiction.isBlank(), "Country of jurisdiction cannot be blank");
        failIf(countryOfJurisdiction.length() != 2,
            "Country of jurisdiction (a country code) must have a length of 2");
    }
}
