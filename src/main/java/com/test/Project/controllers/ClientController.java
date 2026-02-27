package com.test.Project.controllers;

import com.test.Project.dto.ClientDTO;
import com.test.Project.models.Client;
import com.test.Project.services.BookingService;
import com.test.Project.services.ClientService;
import com.test.Project.util.ClientException;
import com.test.Project.util.ClientNotCreatedException;
import com.test.Project.util.ClientNotDeletedException;
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
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clients")
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

    @GetMapping()
    public List<ClientDTO> getClients()
    {
        return clientService.findAll().stream().map(this::convertToClientDTO).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDTO getClient(@PathVariable("id") int id)
    {
        return convertToClientDTO(clientService.findOne(id));
    }

    @PostMapping
    public HttpStatus createClient(@RequestBody @Valid ClientDTO clientDTO, BindingResult result)
    {
        clientValidator.validate(clientDTO,result);
        processValid(result);
        clientService.save(convertToClient(clientDTO));
        return HttpStatus.OK;
    }

    @PatchMapping("/{id}")
    public HttpStatus updateClient(@RequestBody @Valid ClientDTO clientDTO,@PathVariable("id") int id, BindingResult result)
    {
        processValid(result);
        clientService.update(clientDTO,id);
        return HttpStatus.OK;

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteClient(@PathVariable("id") int id)
    {
        clientService.delete(id);
        return new ResponseEntity<>("This client was successfully deleted",HttpStatus.OK);
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
    @ExceptionHandler
    private ResponseEntity<String> handleException(ClientNotDeletedException e)
    {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    private void processValid(BindingResult result)
    {
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
    }
}
