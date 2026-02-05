package dev.ailuruslabs.ailurusbank.domain.clients;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record Organization(
    UUID id,
    ClientType type,
    Instant createdAt,
    OrganizationDetails details) implements Client {

    public Organization {
        Objects.requireNonNull(id, "UUID cannot be null");
        Objects.requireNonNull(type, "Client type cannot be null");
        Objects.requireNonNull(createdAt, "Created at cannot be null");
    }

}
