package validators;

import domain.User;

public class UserValidator implements Validator<User>{
    @Override
    public void validate(User entity) throws ValidationException{
        boolean isUpperCase = Character.isUpperCase(entity.getFirstName().charAt(0)) && Character.isUpperCase(entity.getLastName().charAt(0));
        if (!isUpperCase)
        {
            throw new ValidationException("The username characters must begin with uppercases!");
        }
    }
}
