package com.example.vehicalRentalSystem.service.impl;

import com.example.vehicalRentalSystem.constant.VehicleType;
import com.example.vehicalRentalSystem.dao.BookingRepository;
import com.example.vehicalRentalSystem.model.Booking;
import com.example.vehicalRentalSystem.model.Vehicle;
import com.example.vehicalRentalSystem.model.dto.BookingDto;
import com.example.vehicalRentalSystem.service.BookingService;
import com.example.vehicalRentalSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    private final VehicleService vehicleService;

    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository, VehicleService vehicleService) {
        this.bookingRepository = bookingRepository;
        this.vehicleService = vehicleService;
    }

    @Override
    public void checkforDynamicPricing(String branchId) {
        long totalVehicles = vehicleService.getVehiclesByBranchId(branchId).size();
        long bookedVehicles = bookingRepository.countDistinctVehicleIdByBranchId(branchId);
        if (((bookedVehicles * 100) / totalVehicles) >= 80)
            vehicleService.updateVehiclePrice(branchId);
    }

    @Override
    public Integer addBooking(BookingDto bookingDto) {
        List<Vehicle> vehicles = vehicleService.getAvailableVehicles(bookingDto.getBranchId(), bookingDto.getStartTime(), bookingDto.getEndTime());
        Booking booking = new Booking();
        for (Vehicle vehicle : vehicles
        ) {
            if (vehicle.getType() == VehicleType.valueOf(bookingDto.getVehicleType())) {
                booking.setStartTime(bookingDto.getStartTime());
                booking.setEndTime(bookingDto.getEndTime());
                booking.setVehicle(vehicle);
                booking.setBranchId(bookingDto.getBranchId());
                Integer totalTime = bookingDto.getEndTime() - bookingDto.getStartTime();
                booking.setPrice(vehicle.getPrice() * totalTime);
                bookingRepository.save(booking);
                vehicleService.markVehicleBooked(vehicle.getId());
                checkforDynamicPricing(bookingDto.getBranchId());
                return booking.getPrice();
            }
        }
        return -1;
    }
}
