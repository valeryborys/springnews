package by.academy.springnews.service.impl;

import by.academy.springnews.model.User;
import by.academy.springnews.service.ServiceException;
import by.academy.springnews.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class UserValidationService implements Validator {

    @Autowired
    private UserService userService;


    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "required.message", "required");
        if (user.getUsername().length() < 4 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Username length should be more than 4, but less than 32 characters", "Username length should be more than 4, but less than 32 characters");
        }

        try {
            if (userService.find(user.getUsername()) != null) {
                errors.rejectValue("username", "user.isAlreadyExists", "User with this Username is already Exists");
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "required.message", "required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Password length should be more than 8, but less than 32 characters", "Password length should contain at least 8, but less than 32 characters");
        }
        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Passwords do not match", "Passwords do not match");
        }

    }
}
