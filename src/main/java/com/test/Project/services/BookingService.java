package com.test.Project.services;

import com.test.Project.models.Booking;
import com.test.Project.models.Client;
import com.test.Project.repositories.ClientRepository;
import com.test.Project.repositories.SportsFieldRepository;
import com.test.Project.util.ClientException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookingService {
    private final SportsFieldRepository sportsFieldRepository;
    private final ClientRepository clientRepository;
    @Autowired
    public BookingService(SportsFieldRepository sportsFieldRepository, ClientRepository clientRepository) {
        this.sportsFieldRepository = sportsFieldRepository;
        this.clientRepository = clientRepository;
    }

    @Transactional
    public void save(Booking booking)
    {
        Optional<Client> optionalClient = clientRepository.findClientByMailIgnoreCase(booking.getClient().getMail());
        Client client = optionalClient.orElseThrow(ClientException::new);

    }
}
