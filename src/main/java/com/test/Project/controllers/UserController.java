package com.test.Project.controllers;


import com.test.Project.models.User;
import com.test.Project.services.RegistrationService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping()
public class UserController {

    private final RegistrationService registrationService;
    private final ModelMapper modelMapper;

    @Autowired
    public UserController(RegistrationService registrationService, ModelMapper modelMapper) {
        this.registrationService = registrationService;
        this.modelMapper = modelMapper;
    }



    @PostMapping("/registration")
    public HttpStatus create(@RequestBody User user)
    {

        registrationService.register(user);
        return HttpStatus.OK;
    }


}
