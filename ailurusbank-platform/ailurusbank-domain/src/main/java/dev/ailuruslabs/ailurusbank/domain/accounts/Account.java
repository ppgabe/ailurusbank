package dev.ailuruslabs.ailurusbank.domain.accounts;

import java.math.BigInteger;
import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;

public record Account(
    UUID id,
    UUID branchId,
    AccountNumber accountNumber,
    String currencyCode,
    BigInteger balanceMinorUnits,
    AccountType type,
    AccountStatus status,
    Instant createdAt
) {
    public Account {
        Objects.requireNonNull(id, "ID cannot be null");
        Objects.requireNonNull(branchId, "Branch ID cannot be null");

        Objects.requireNonNull(accountNumber, "Account number cannot be null");

        Objects.requireNonNull(currencyCode, "Currency code cannot be null");
        failIf(currencyCode.length() != 3, "Currency code must have a length of 3");

        Objects.requireNonNull(balanceMinorUnits, "Balance cannot be null");
        failIf(balanceMinorUnits.compareTo(BigInteger.ZERO) < 0,
            "Balance in minor units must be greater than or equal to 0");

        Objects.requireNonNull(type, "Type cannot be null");
        Objects.requireNonNull(status, "Status cannot be null");
        Objects.requireNonNull(createdAt, "Creation time cannot be null");
    }
}
