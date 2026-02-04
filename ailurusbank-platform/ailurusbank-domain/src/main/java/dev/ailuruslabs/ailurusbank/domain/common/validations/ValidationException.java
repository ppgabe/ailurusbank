package dev.ailuruslabs.ailurusbank.domain.common.validations;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super(message);
    }
}
