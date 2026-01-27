package com.test.Project.services;


import com.test.Project.models.Booking;
import com.test.Project.models.Client;
import com.test.Project.models.SportsField;
import com.test.Project.repositories.BookingRepository;
import com.test.Project.repositories.ClientRepository;
import com.test.Project.repositories.SportsFieldRepository;
import com.test.Project.util.ClientException;
import com.test.Project.util.SportsFieldException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BookingService {
    private final SportsFieldRepository sportsFieldRepository;
    private final ClientRepository clientRepository;
    private final BookingRepository bookingRepository;

    @Autowired
    public BookingService(SportsFieldRepository sportsFieldRepository, ClientRepository clientRepository, BookingRepository bookingRepository) {
        this.sportsFieldRepository = sportsFieldRepository;
        this.clientRepository = clientRepository;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    public void save(Booking booking)
    {
        Optional<Client> optionalClient = clientRepository.findClientByMailIgnoreCase(booking.getClient().getMail());
        Client client = optionalClient.orElseThrow(ClientException::new);
        booking.setClient(client);
        Optional<SportsField> optionalSportsField = sportsFieldRepository.findSportsFieldByName(booking.getSportsField().getName());
        SportsField sportsField = optionalSportsField.orElseThrow(SportsFieldException::new);
        booking.setSportsField(sportsField);
        bookingRepository.save(booking);


    }
}
