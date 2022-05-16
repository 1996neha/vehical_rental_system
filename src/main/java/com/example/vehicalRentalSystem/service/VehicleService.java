package com.example.vehicalRentalSystem.service;

import com.example.vehicalRentalSystem.model.Vehicle;
import com.example.vehicalRentalSystem.model.dto.AddVehicleDto;
import com.example.vehicalRentalSystem.model.dto.GetVehicleDto;

import java.util.List;

public interface VehicleService {
    public Boolean addVehicle(AddVehicleDto vehicleDto);

    public boolean isVehicleAvailable(Vehicle vehicle, Integer startTime, Integer endTime);

    public List<Vehicle> getAvailableVehicles(String branchId, Integer startTime, Integer endTime);

    public String displayAvailableVehicles(GetVehicleDto vehicleDto);

    public List<Vehicle> getVehiclesByBranchId(String branchId);

    public void markVehicleBooked(String id);

    public void updateVehiclePrice(String branchId);
}
