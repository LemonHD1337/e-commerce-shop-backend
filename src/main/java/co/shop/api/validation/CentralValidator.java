package co.shop.api.validation;

import co.shop.api.exception.BadRequestException;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import java.util.Set;

public class CentralValidator {

    private final Validator validator;

    public CentralValidator() {
        try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
            this.validator = factory.getValidator();
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    public <T> void validate(T object) throws IllegalArgumentException {
        Set<ConstraintViolation<T>> violations = validator.validate(object);

        if (!violations.isEmpty()) {
            StringBuilder errors = new StringBuilder();
            for (ConstraintViolation<T> violation : violations) {
                errors.append(violation.getMessage()).append("\n");
            }
            throw new BadRequestException(errors.toString());
        }
    }
}
