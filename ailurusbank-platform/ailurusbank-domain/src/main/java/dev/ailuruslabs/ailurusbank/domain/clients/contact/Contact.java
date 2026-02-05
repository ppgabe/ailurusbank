package dev.ailuruslabs.ailurusbank.domain.clients.contact;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record Contact(
    UUID id,
    Instant createdAt,
    ContactDetails details
) {

    public Contact {
        Objects.requireNonNull(id, "ID cannot be null");
        Objects.requireNonNull(createdAt, "Creation time cannot be null");
        Objects.requireNonNull(details, "Details cannot be null");
    }

}
