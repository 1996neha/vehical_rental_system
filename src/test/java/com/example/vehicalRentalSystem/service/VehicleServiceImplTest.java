package com.example.vehicalRentalSystem.service;

import com.example.vehicalRentalSystem.constant.VehicleType;
import com.example.vehicalRentalSystem.dao.BookingRepository;
import com.example.vehicalRentalSystem.dao.VehicleRepository;
import com.example.vehicalRentalSystem.model.dto.AddVehicleDto;
import com.example.vehicalRentalSystem.model.dto.BookingDto;
import com.example.vehicalRentalSystem.model.dto.GetVehicleDto;
import com.example.vehicalRentalSystem.service.impl.BookingServiceImpl;
import com.example.vehicalRentalSystem.service.impl.VehicleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

public class VehicleServiceImplTest {
    private VehicleServiceImpl vehicleService;
    private AddVehicleDto addVehicleDto;
    private GetVehicleDto getVehicleDto;
    @Mock
    private VehicleRepository vehicleRepository;
    @Mock
    private BranchService branchService;
    @Mock
    private BookingRepository bookingRepository;

    @Before
    public void init() {
        vehicleService = new VehicleServiceImpl(vehicleRepository, branchService, bookingRepository);
    }

//    @Test
//    public void testAddVehicle_shouldReturnTrue() {
//    }
//
//    @Test
//    public void testDisplayVehicles_shouldReturnVehicles() {
//    }

}
