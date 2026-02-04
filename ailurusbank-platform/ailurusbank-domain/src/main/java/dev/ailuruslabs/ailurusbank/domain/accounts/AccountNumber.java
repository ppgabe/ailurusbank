package dev.ailuruslabs.ailurusbank.domain.accounts;

import dev.ailuruslabs.ailurusbank.domain.common.validations.ValidationException;

import java.util.*;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;
import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.require;

public record AccountNumber(String value) {

    public AccountNumber {
        Objects.requireNonNull(value, "Value cannot be null");
        failIf(value.isBlank(), "Value cannot be blank");

        require(isValid(value), "Value does not have a valid ISO 7064 checksum");
    }

    private static boolean isValid(String value) {
        long runningRemainder = 0;

        for (int i = 0; i < value.length(); i++) {
            var currentChar = value.charAt(i);

            if (Character.isUpperCase(currentChar)) {
                runningRemainder = ((runningRemainder * 100) + (currentChar - 55)) % 97;
            } else if (Character.isDigit(currentChar)) {
                runningRemainder = ((runningRemainder * 10) + (currentChar - 48)) % 97;
            } else {
                throw new ValidationException("Account number must only contain uppercase letters or digits");
            }
        }

        return runningRemainder == 1;
    }

    @Override
    public String toString() {
        return value;
    }
}

