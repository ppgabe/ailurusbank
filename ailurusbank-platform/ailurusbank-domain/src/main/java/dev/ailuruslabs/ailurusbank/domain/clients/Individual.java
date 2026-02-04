package dev.ailuruslabs.ailurusbank.domain.clients;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record Individual(
    UUID id,
    ClientType type,
    Instant createdAt,
    String firstName,
    String middleName,
    String lastName,
    String nationality,
    LocalDate birthDate
) implements Client {

    public Individual {

        Objects.requireNonNull(id, "UUID cannot be null");
        Objects.requireNonNull(type, "Client type cannot be null");
        Objects.requireNonNull(createdAt, "Created at cannot be null");
        Objects.requireNonNull(firstName, "First name cannot be null");
        Objects.requireNonNull(lastName, "Last name cannot be null");
        Objects.requireNonNull(nationality, "Nationality cannot be null");
        Objects.requireNonNull(birthDate, "Birth date cannot be null");

        failIf(firstName.isBlank(), "First name cannot be blank");
        failIf(lastName.isBlank(), "Last name cannot be blank");

        failIf(nationality.isBlank(), "Nationality cannot be blank");
        failIf(nationality.length() != 2, "Nationality (a country code) must have a length of 2");
    }
}
