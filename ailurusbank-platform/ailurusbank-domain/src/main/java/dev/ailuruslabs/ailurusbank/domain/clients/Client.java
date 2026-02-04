package dev.ailuruslabs.ailurusbank.domain.clients;

import java.time.Instant;
import java.util.UUID;

public sealed interface Client permits Individual, Organization {
    UUID id();

    ClientType type();

    Instant createdAt();
}

