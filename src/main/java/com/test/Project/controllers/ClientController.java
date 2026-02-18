package com.test.Project.controllers;

import com.test.Project.dto.ClientDTO;
import com.test.Project.models.Client;
import com.test.Project.services.BookingService;
import com.test.Project.services.ClientService;
import com.test.Project.util.ClientException;
import com.test.Project.util.ClientNotCreatedException;
import com.test.Project.util.ClientValidator;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {
    private final ClientService clientService;
    private final ModelMapper modelMapper;
    private final ClientValidator clientValidator;




    @Autowired
    public ClientController(ClientService clientService, ModelMapper modelMapper, ClientValidator clientValidator) {
        this.clientService = clientService;
        this.modelMapper = modelMapper;
        this.clientValidator = clientValidator;
    }

    @GetMapping("/{id}")
    public HttpStatus getClient(@PathVariable int id)
    {
        clientService.findOne(id);
        return HttpStatus.OK;
    }

    @PostMapping
    public HttpStatus createClient(@RequestBody @Valid ClientDTO clientDTO, BindingResult result)
    {
        clientValidator.validate(clientDTO,result);

        if(result.hasErrors())
        {
            StringBuilder stringBuilder = new StringBuilder();
            List<FieldError> errors = result.getFieldErrors();
            for(FieldError error:errors)
                stringBuilder.append(error.getField()).
                        append(" - ").
                        append(error.getDefaultMessage())
                        .append(";");
            throw new ClientNotCreatedException(stringBuilder.toString());
        }
        clientService.save(convertToClient(clientDTO));
        return HttpStatus.OK;
    }
    @GetMapping("/clients")
    public List<Client> getClient()
    {
        return clientService.findAll();
    }

    private Client convertToClient(ClientDTO clientDTO) {
        return modelMapper.map(clientDTO,Client.class);
    }

    private ClientDTO convertToClientDTO(Client person)
    {
        return modelMapper.map(person,ClientDTO.class);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(ClientNotCreatedException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    private ResponseEntity<String> handleException(ClientException e)
    {
        return new ResponseEntity<>("client not found", HttpStatus.BAD_REQUEST);
    }

}
