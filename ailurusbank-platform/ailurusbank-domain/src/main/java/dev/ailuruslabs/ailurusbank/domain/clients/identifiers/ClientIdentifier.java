package dev.ailuruslabs.ailurusbank.domain.clients.identifiers;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

public record ClientIdentifier(
    UUID id,
    UUID clientId,
    Instant createdAt,
    IdentifierDetails details
) {

    public ClientIdentifier {
        Objects.requireNonNull(id, "ID cannot be null");
        Objects.requireNonNull(clientId, "Client ID cannot be null");
        Objects.requireNonNull(createdAt, "Creation time cannot be null");
        Objects.requireNonNull(details);
    }
}
