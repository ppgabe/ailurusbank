package dev.ailuruslabs.ailurusbank.domain.banks;

import java.util.Objects;
import java.util.UUID;

public record Branch(
    UUID id,
    UUID bankId,
    BranchDetails details
) {
    public Branch {
        Objects.requireNonNull(id, "ID cannot be null");
        Objects.requireNonNull(bankId, "Bank ID cannot be null");
        Objects.requireNonNull(details, "Details cannot be null");
    }
}
