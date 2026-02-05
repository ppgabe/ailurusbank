package dev.ailuruslabs.ailurusbank.domain.banks;

import java.util.Objects;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;
import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.require;

public record BankDetails(
    String institutionCode,
    String countryCode,
    String name,
    String locationCode) {

    public BankDetails {
        Objects.requireNonNull(institutionCode, "Institution code cannot be null");
        Objects.requireNonNull(countryCode, "Country code cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(locationCode, "Location code cannot be null");

        failIf(institutionCode.isBlank(), "Institution code cannot be blank");
        failIf(name.isBlank(), "Name cannot be blank");

        failIf(locationCode.isBlank(), "Location code cannot be blank");
        failIf(locationCode.length() != 2, "Location code must have a length of 2");

        for (int i = 0; i < institutionCode.length(); i++) {
            require(Character.isUpperCase(institutionCode.charAt(i)),
                "Institution code must only contain uppercase letters");
        }

        for (int i = 0; i < locationCode.length(); i++) {
            require(Character.isUpperCase(locationCode.charAt(i)),
                "Location code must only contain uppercase letters");
        }
    }

}
