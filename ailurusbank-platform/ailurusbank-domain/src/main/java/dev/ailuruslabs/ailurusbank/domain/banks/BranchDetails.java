package dev.ailuruslabs.ailurusbank.domain.banks;

import java.util.Objects;
import java.util.UUID;

import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.failIf;
import static dev.ailuruslabs.ailurusbank.domain.common.validations.Validations.require;

public record BranchDetails(
    String branchCode,
    String name,
    String streetAddress,
    String city) {

    public BranchDetails {
        Objects.requireNonNull(branchCode, "Branch code cannot be null");
        Objects.requireNonNull(name, "Name cannot be null");
        Objects.requireNonNull(streetAddress, "Street address cannot be null");
        Objects.requireNonNull(city, "City cannot be null");

        failIf(branchCode.isBlank(), "Branch code cannot be blank");
        failIf(branchCode.length() > 3, "Branch code cannot have a length greater than 3");

        failIf(name.isBlank(), "Name cannot be blank");

        failIf(streetAddress.isBlank(), "Street address cannot be blank");
        failIf(city.isBlank(), "City cannot be blank");

        for (int i = 0; i < branchCode.length(); i++) {
            require(Character.isUpperCase(branchCode.charAt(i)) || Character.isDigit(branchCode.charAt(i)),
                "Branch code must only contain uppercase letters or digits");
        }
    }
}
