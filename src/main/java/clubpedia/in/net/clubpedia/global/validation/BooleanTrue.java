package clubpedia.in.net.clubpedia.global.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = BooleanTrueValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface BooleanTrue {
    String message() default "필수 동의 항목입니다.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}