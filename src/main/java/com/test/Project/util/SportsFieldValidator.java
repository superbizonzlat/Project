package com.example.FirstRestApp.util;

import com.example.FirstRestApp.dto.PersonDTO;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class PersonValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return PersonDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        PersonDTO personDTO = (PersonDTO) target;

        if(!(personDTO.getName().length() > 2 && personDTO.getName().length() <= 10))
            errors.rejectValue("name", "", "Name should be between 2 and 10 characters");
    }
}
