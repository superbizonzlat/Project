package com.test.Project.controllers;

import com.test.Project.dto.BookingDTO;
import com.test.Project.dto.ClientDTO;
import com.test.Project.models.Booking;
import com.test.Project.models.Client;
import com.test.Project.services.BookingService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;
    private final ModelMapper modelMapper;
    @Autowired
    public BookingController(BookingService bookingService, ModelMapper modelMapper) {
        this.bookingService = bookingService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    public HttpStatus createBooking(@RequestBody BookingDTO bookingDTO)
    {

        System.out.println(bookingDTO);

        bookingService.save(convertToBooking(bookingDTO));
        return HttpStatus.OK;
    }

    private Booking convertToBooking(BookingDTO bookingDTO) {
        return modelMapper.map(bookingDTO,Booking.class);
    }

    private BookingDTO convertToBookingDTO(Booking booking)
    {
        return modelMapper.map(booking,BookingDTO.class);
    }


}
