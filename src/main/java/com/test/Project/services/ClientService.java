package com.test.Project.services;

import com.test.Project.dto.ClientDTO;
import com.test.Project.dto.SportsFieldDTO;
import com.test.Project.models.Client;
import com.test.Project.models.Place;
import com.test.Project.models.Sport;
import com.test.Project.models.SportsField;
import com.test.Project.repositories.ClientRepository;
import com.test.Project.util.ClientException;
import com.test.Project.util.SportFieldsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ClientService {
    private final ClientRepository clientRepository;

    private final ModelMapper modelMapper;
    @Autowired
    public ClientService(ClientRepository clientRepository, ModelMapper modelMapper) {
        this.clientRepository = clientRepository;
        this.modelMapper = modelMapper;
    }


    public Client findOne(int id)
    {
        Optional<Client> foundClient = clientRepository.findById(id);
        return foundClient.orElseThrow(ClientException::new);
    }

    public List<Client> findAll()
    {
        return clientRepository.findAll();
    }
    @Transactional
    public void save(Client client)
    {

        clientRepository.save(client);

    }

    @Transactional
    public void delete(int id)
    {
        Client client = findOne(id);
        clientRepository.delete(client);

    }
    @Transactional
    public void update(ClientDTO clientDTO, int id)
    {
        Client client = findOne(id);
        modelMapper.map(clientDTO, client);
        clientRepository.save(client);
    }

    public boolean findClientByTelephone(String telephone)
    {
        Optional<Client> clientOptional = clientRepository.findClientByTelephone(telephone);
        return clientOptional.isPresent();
    }

    public boolean findClientByMail(String mail)
    {
        Optional<Client> clientOptional = clientRepository.findClientByMailIgnoreCase(mail);
        return clientOptional.isPresent();
    }
}
