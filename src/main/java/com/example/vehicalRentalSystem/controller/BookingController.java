package com.example.vehicalRentalSystem.controller;

import com.example.vehicalRentalSystem.model.dto.BookingDto;
import com.example.vehicalRentalSystem.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/book")
public class BookingController {
    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping
    public ResponseEntity<Integer> book(@Valid @RequestBody BookingDto bookingDto) {
        return new ResponseEntity<Integer>(bookingService.addBooking(bookingDto), HttpStatus.OK);
    }
}
