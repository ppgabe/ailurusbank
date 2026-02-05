package dev.ailuruslabs.ailurusbank.domain.accounts;

import java.util.Objects;
import java.util.UUID;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record AccountDetails(
    UUID branchId,
    String currencyCode,
    AccountType type,
    AccountStatus status
) {
    public AccountDetails {
        Objects.requireNonNull(branchId, "Branch ID cannot be null");

        Objects.requireNonNull(currencyCode, "Currency alpha2 cannot be null");
        failIf(currencyCode.length() != 3, "Currency alpha2 must have a length of 3");

        Objects.requireNonNull(type, "Type cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
    }
}
