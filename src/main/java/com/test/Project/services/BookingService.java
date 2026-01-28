package com.test.Project.services;


import com.test.Project.dto.BookingDTO;
import com.test.Project.models.*;
import com.test.Project.repositories.BookingRepository;
import com.test.Project.repositories.ClientRepository;
import com.test.Project.repositories.SportsFieldRepository;
import com.test.Project.util.BookingException;
import com.test.Project.util.ClientException;
import com.test.Project.util.SportsFieldException;
import org.modelmapper.ModelMapper;
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
    private final ModelMapper modelMapper;

    @Autowired
    public BookingService(SportsFieldRepository sportsFieldRepository, ClientRepository clientRepository, BookingRepository bookingRepository, ModelMapper modelMapper) {
        this.sportsFieldRepository = sportsFieldRepository;
        this.clientRepository = clientRepository;
        this.bookingRepository = bookingRepository;
        this.modelMapper = modelMapper;
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
    @Transactional
    public void update(BookingDTO bookingDTO, int id)
    {
        Optional<Booking> optionalBooking = bookingRepository.findById(id);
        Booking booking = optionalBooking.orElseThrow(BookingException::new);
        if (bookingDTO.getClientDTO() != null)
        {
            Optional<Client> optionalClient = clientRepository.findClientByMailIgnoreCase(bookingDTO.getClientDTO().getMail());
            booking.setClient(optionalClient.orElseThrow(ClientException::new));
            bookingDTO.setClientDTO(null);
        }
        if (bookingDTO.getSportsFieldDTO() != null)
        {
            Optional<SportsField> optionalSportsField = sportsFieldRepository.findSportsFieldByName(bookingDTO.getSportsFieldDTO().getName();
            booking.setSportsField(optionalSportsField.orElseThrow(SportsFieldException::new));
            bookingDTO.setSportsFieldDTO(null);
        }

        modelMapper.map(bookingDTO, booking);
    }
}
