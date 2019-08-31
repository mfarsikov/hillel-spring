package hillel.spring.petclinic.store;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class LatinNameValidator implements ConstraintValidator<LatinName, String> {
    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return value == null || value.contains("um") || value.contains("us");
    }
}
