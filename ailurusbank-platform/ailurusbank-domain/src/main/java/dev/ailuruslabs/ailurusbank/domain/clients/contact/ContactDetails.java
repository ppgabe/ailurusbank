package dev.ailuruslabs.ailurusbank.domain.clients.contact;

import java.util.Objects;
import java.util.UUID;

public record ContactDetails(
    ContactPrecedence precedence,
    String type,
    String value,
    String description,
    UUID clientId) {

    public ContactDetails {
        Objects.requireNonNull(precedence, "Precedence cannot be null");
        Objects.requireNonNull(type, "Type cannot be null");
        Objects.requireNonNull(value, "Value cannot be null");
        Objects.requireNonNull(clientId, "Client ID cannot be null");

        if (type.isBlank()) {
            throw new IllegalArgumentException("Type cannot be blank");
        }

        if (value.isBlank()) {
            throw new IllegalArgumentException("Value cannot be blank");
        }
    }
}
