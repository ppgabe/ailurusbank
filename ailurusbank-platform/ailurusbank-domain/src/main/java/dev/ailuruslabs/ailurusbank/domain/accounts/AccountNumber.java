package dev.ailuruslabs.ailurusbank.domain.accounts;

import dev.ailuruslabs.ailurusbank.domain.common.validations.ValidationException;

import java.util.*;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;
import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.require;

public record AccountNumber(String value) {

    public static AccountNumber of(String value) throws ValidationException {
        return new AccountNumber(value);
    }

    public AccountNumber {
        Objects.requireNonNull(value, "Value cannot be null");
        failIf(value.isBlank(), "Value cannot be blank");
        failIf(value.length() > 20, "Value cannot have a length greater than 20");

        require(hasValidChecksum(value), "Value does not have a valid ISO 7064 checksum");
    }

    /**
     * Checks whether the given String value has a valid ISO 7064 Mod 97-10 checksum.
     * Calculates the checksum using a running remainder.
     * @param value The value to be checked.
     * @return <alpha2>true</alpha2> if value has a valid checksum, <alpha2>false</alpha2> if otherwise.
     * @throws ValidationException If the value contains invalid characters.
     */
    public static boolean hasValidChecksum(String value) throws ValidationException {
        long runningRemainder = 0;

        for (int i = 0; i < value.length(); i++) {
            var currentChar = value.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                runningRemainder = ((runningRemainder * 100) + (currentChar - 55)) % 97;
            } else if (Character.isDigit(currentChar)) {
                runningRemainder = ((runningRemainder * 10) + (currentChar - 48)) % 97;
            } else {
                throw new ValidationException("Value must only contain uppercase letters or digits");
            }
        }

        return runningRemainder == 1;
    }

    @Override
    public String toString() {
        return value;
    }
}

