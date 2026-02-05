package dev.ailuruslabs.ailurusbank.domain.banks;

import java.util.Objects;
import java.util.UUID;

public record Bank(
    UUID id,
    BankDetails details) {
    public Bank {
        Objects.requireNonNull(id, "ID cannot be null");
        Objects.requireNonNull(details, "Details cannot be null");
    }
}
