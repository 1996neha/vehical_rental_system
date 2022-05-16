package com.example.vehicalRentalSystem.service;

import com.example.vehicalRentalSystem.constant.VehicleStatus;
import com.example.vehicalRentalSystem.constant.VehicleType;
import com.example.vehicalRentalSystem.dao.BookingRepository;
import com.example.vehicalRentalSystem.model.Booking;
import com.example.vehicalRentalSystem.model.Branch;
import com.example.vehicalRentalSystem.model.BranchId;
import com.example.vehicalRentalSystem.model.Vehicle;
import com.example.vehicalRentalSystem.model.dto.AddVehicleDto;
import com.example.vehicalRentalSystem.model.dto.BookingDto;
import com.example.vehicalRentalSystem.service.impl.BookingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class BookingServiceImplTest {
    private BookingServiceImpl bookingService;
    private BookingDto bookingDto;
    @Mock
    private BookingRepository bookingRepository;
    @Mock
    private VehicleService vehicleService;

    @Before
    public void init() {
        bookingService = new BookingServiceImpl(bookingRepository, vehicleService);
        bookingDto = BookingDto.builder().branchId("B1").vehicleType("BIKE").startTime(3).endTime(4).build();
    }

    @Test
    public void testBookSuccess_shouldReturnPrice() {
        BranchId branchId = BranchId.builder().id("B1").vehicleType(VehicleType.BIKE).build();
        Branch branch = Branch.builder().branchId(branchId).build();
        Vehicle vehicle = Vehicle.builder().price(100).id("V1").type(VehicleType.BIKE).status(VehicleStatus.AVAILABLE).branch(branch).build();
        List<Vehicle> vehicles = new ArrayList<Vehicle>(Arrays.asList(vehicle));
        when(vehicleService.getAvailableVehicles(anyString(),ArgumentMatchers.anyInt(),anyInt())).thenReturn(vehicles);
        Booking booking = Booking.builder().price(100).startTime(3).endTime(4).branchId("B1").vehicle(vehicle).build();
        when(bookingRepository.save(booking)).thenReturn(null);
        doNothing().when(vehicleService).markVehicleBooked("V1");
        when(vehicleService.getVehiclesByBranchId("B1")).thenReturn(vehicles);
        when(bookingRepository.countDistinctVehicleIdByBranchId("B1")).thenReturn(0L);
        Integer price = bookingService.addBooking(bookingDto);
        Assert.assertEquals(booking.getPrice(),price);
    }

}
