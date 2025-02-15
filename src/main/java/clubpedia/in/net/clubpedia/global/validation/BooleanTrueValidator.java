package clubpedia.in.net.clubpedia.global.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class BooleanTrueValidator implements ConstraintValidator<BooleanTrue, Boolean> {

    @Override
    public boolean isValid(Boolean value, ConstraintValidatorContext context) {
        return value != null && value;
    }
}
