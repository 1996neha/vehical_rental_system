package com.example.vehicalRentalSystem.service.impl;

import com.example.vehicalRentalSystem.constant.VehicleType;
import com.example.vehicalRentalSystem.dao.BookingRepository;
import com.example.vehicalRentalSystem.dao.VehicleRepository;
import com.example.vehicalRentalSystem.exception.BadTimestampException;
import com.example.vehicalRentalSystem.exception.VehicleNotFoundException;
import com.example.vehicalRentalSystem.model.Booking;
import com.example.vehicalRentalSystem.model.Branch;
import com.example.vehicalRentalSystem.model.Vehicle;
import com.example.vehicalRentalSystem.constant.VehicleStatus;
import com.example.vehicalRentalSystem.model.dto.AddVehicleDto;
import com.example.vehicalRentalSystem.model.dto.GetVehicleDto;
import com.example.vehicalRentalSystem.service.BranchService;
import com.example.vehicalRentalSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {
    private final VehicleRepository vehicleRepository;
    private final BranchService branchService;
    private final BookingRepository bookingRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, BranchService branchService, BookingRepository bookingRepository) {
        this.vehicleRepository = vehicleRepository;
        this.branchService = branchService;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Boolean addVehicle(AddVehicleDto vehicleDto) {
        Branch branch = branchService.getBranch(vehicleDto.getBranchId(), VehicleType.valueOf(vehicleDto.getVehicleType()));
        if (branch == null) return Boolean.FALSE;
        Vehicle vehicle = new Vehicle();
        vehicle.setId(vehicleDto.getVehicleId());
        vehicle.setStatus(VehicleStatus.AVAILABLE);
        vehicle.setType(VehicleType.valueOf(vehicleDto.getVehicleType()));
        vehicle.setBranch(branch);
        vehicle.setPrice(vehicleDto.getPrice());
        vehicleRepository.save(vehicle);
        return Boolean.TRUE;
    }

    @Override
    public boolean isVehicleAvailable(Vehicle vehicle, Integer startTime, Integer endTime) {
        List<Booking> bookings = bookingRepository.findAllByVehicleId(vehicle.getId());
        for (Booking booking : bookings
        ) {
            Integer bStartTime = booking.getStartTime();
            Integer bEndTime = booking.getEndTime();
            List<Integer> list1 = new ArrayList<>();
            List<Integer> list2 = new ArrayList<>();
            for (int i = startTime; i <= endTime; i++) {
                list1.add(i);
            }
            for (int i = bStartTime; i <= bEndTime; i++) {
                list2.add(i);
            }
            list1.retainAll(list2);
            if (list1.size() > 1)
                return false;
//            if (startTime == bStartTime ||
//                    endTime == bEndTime ||
//                    (startTime > bStartTime && startTime < bEndTime) ||
//                    (endTime > bStartTime && endTime < bEndTime))
//                return false;
        }
        return true;
    }

    @Override
    public List<Vehicle> getAvailableVehicles(String branchId, Integer startTime, Integer endTime) {
        if (startTime >= endTime) throw new BadTimestampException("Start time must be less than End time");
        List<Vehicle> vehicles = vehicleRepository.findAllByOrderByPriceAsc()
                .stream()
                .filter(vehicle ->
                        vehicle.getBranch().getBranchId().getId().equals(branchId) &&
                                isVehicleAvailable(vehicle, startTime, endTime))
                .collect(Collectors.toList());
        return vehicles;
    }

    @Override
    public String displayAvailableVehicles(GetVehicleDto vehicleDto) {
        List<Vehicle> vehicles = getAvailableVehicles(vehicleDto.getBranchId(), vehicleDto.getStartTime(), vehicleDto.getEndTime());
        String commaSeparatedStr = vehicles
                .stream()
                .map(vehicle -> vehicle.getId())
                .collect(Collectors.joining(","));
        return commaSeparatedStr;
    }

    @Override
    public List<Vehicle> getVehiclesByBranchId(String branchId) {
        List<Vehicle> vehicles = vehicleRepository.findAllByOrderByPriceAsc()
                .stream()
                .filter(vehicle ->
                        vehicle.getBranch().getBranchId().getId().equals(branchId))
                .collect(Collectors.toList());
        return vehicles;
    }

    @Override
    public void markVehicleBooked(String id) {
        Vehicle vehicle = vehicleRepository.findById(id).orElseThrow(() -> new VehicleNotFoundException("false"));
        vehicle.setStatus(VehicleStatus.BOOKED);
        vehicleRepository.save(vehicle);
    }

    @Override
    public void updateVehiclePrice(String branchId) {
        List<Vehicle> vehicles = getVehiclesByBranchId(branchId)
                .stream()
                .filter(vehicle -> vehicle.getStatus() == VehicleStatus.AVAILABLE)
                .collect(Collectors.toList());
        for (Vehicle vehicle : vehicles) {
            Integer price = vehicle.getPrice();
            Integer newPrice = (int) 1.1 * price;
            vehicle.setPrice(newPrice);
            vehicleRepository.save(vehicle);
        }
    }
}
