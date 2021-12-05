package validators;

import javax.validation.ValidationException;

public interface Validator<T> {
    void validate(T entity) throws ValidationException;
}
