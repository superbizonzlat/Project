package com.test.Project.controllers;

import com.test.Project.dto.BookingDTO;
import com.test.Project.models.Booking;
import com.test.Project.services.BookingService;
import com.test.Project.util.BookingNotCreatedException;
import com.test.Project.util.ClientException;
import com.test.Project.util.ClientNotCreatedException;
import com.test.Project.util.SportsFieldException;
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
    public HttpStatus createBooking(@RequestBody @Valid BookingDTO bookingDTO, BindingResult result)
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
            throw new BookingNotCreatedException(stringBuilder.toString());
        }
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

    @ExceptionHandler
    private ResponseEntity<String> handleException(ClientException e)
    {
        return new ResponseEntity<>("client not found", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<String> handleException(SportsFieldException e)
    {
        return new ResponseEntity<>("sport fields not found", HttpStatus.BAD_REQUEST);
    }

}
