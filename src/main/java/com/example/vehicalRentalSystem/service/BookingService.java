package com.example.vehicalRentalSystem.service;

import com.example.vehicalRentalSystem.model.dto.BookingDto;

public interface BookingService {
    public void checkforDynamicPricing(String branchId);

    public Integer addBooking(BookingDto bookingDto);

}
