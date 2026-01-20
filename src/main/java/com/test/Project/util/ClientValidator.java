package com.test.Project.util;


import com.test.Project.dto.ClientDTO;
import com.test.Project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ClientValidator implements Validator {

    private final ClientService clientService;

    @Autowired
    public ClientValidator(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ClientDTO.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ClientDTO clientDTO = (ClientDTO) target;
        String telephone = clientDTO.getTelephone().replaceFirst("\\+7|8","");
        if(clientService.findClientByTelephone(telephone))
            errors.rejectValue("telephone","","this telephone already exists");
        if(clientService.findClientByMail(clientDTO.getMail()))
            errors.rejectValue("mail","","this mail already exists");
    }
}
