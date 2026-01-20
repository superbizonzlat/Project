package com.test.Project.util;


import com.test.Project.dto.SportsFieldDTO;
import com.test.Project.services.SportFieldsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


@Component
public class SportsFieldValidator implements Validator {

    private final SportFieldsService sportFieldsService;

    @Autowired
    public SportsFieldValidator(SportFieldsService sportFieldsService) {
        this.sportFieldsService = sportFieldsService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return SportsFieldDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SportsFieldDTO sportsFieldDTO = (SportsFieldDTO) target;

        if(!(sportsFieldDTO.getName().length() > 2 && sportsFieldDTO.getName().length() <= 10))
            errors.rejectValue("name", "", "Name should be between 2 and 10 characters");
        if (sportFieldsService.findOne(sportsFieldDTO.getName()))
            errors.rejectValue("name","","this name already exists");
    }
}
