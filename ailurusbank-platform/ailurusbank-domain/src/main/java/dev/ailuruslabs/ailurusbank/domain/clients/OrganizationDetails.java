package dev.ailuruslabs.ailurusbank.domain.clients;

import java.time.LocalDate;
import java.util.Objects;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record OrganizationDetails(
    String name,
    LocalDate incorporationDate,
    String countryOfIncorporation) implements ClientDetails {

    public OrganizationDetails {
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(incorporationDate, "Incorporation date cannot be null");
        Objects.requireNonNull(countryOfIncorporation, "Country of incorporation cannot be null");

        failIf(name.isBlank(), "Name cannot be blank");
        failIf(countryOfIncorporation.isBlank(), "Country of incorporation cannot be blank");

        failIf(countryOfIncorporation.length() != 2,
            "Country of incorporation (a country code) must be 2 characters long");
    }

}
