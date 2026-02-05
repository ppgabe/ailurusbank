package dev.ailuruslabs.ailurusbank.domain.accounts;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record Account(
    UUID id,
    Instant createdAt,
    AccountDetails details
) {
    public Account {
        Objects.requireNonNull(id, "ID cannot be null");
        Objects.requireNonNull(createdAt, "Creation time cannot be null");
        Objects.requireNonNull(details, "Details cannot be null");
    }
}
