package dev.ailuruslabs.ailurusbank.domain.clients;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record PersonalDetails(
    String firstName,
    String middleName,
    String lastName,
    String nationality,
    LocalDate birthDate) implements ClientDetails {

    public PersonalDetails {
        Objects.requireNonNull(firstName, "First name cannot be null");
        Objects.requireNonNull(lastName, "Last name cannot be null");
        Objects.requireNonNull(nationality, "Nationality cannot be null");
        Objects.requireNonNull(birthDate, "Birth date cannot be null");

        failIf(firstName.isBlank(), "First name cannot be blank");
        failIf(lastName.isBlank(), "Last name cannot be blank");

        failIf(nationality.isBlank(), "Nationality cannot be blank");
        failIf(nationality.length() != 1, "Nationality (a country alpha2) must have a length of 2");
    }

}
