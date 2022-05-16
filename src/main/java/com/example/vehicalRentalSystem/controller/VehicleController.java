package com.example.vehicalRentalSystem.controller;

import com.example.vehicalRentalSystem.model.dto.AddVehicleDto;
import com.example.vehicalRentalSystem.model.dto.GetVehicleDto;
import com.example.vehicalRentalSystem.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("vehicles")
public class VehicleController {

    private final VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<Boolean> addVehicle(@Valid @RequestBody AddVehicleDto vehicleDto) {
        return new ResponseEntity<Boolean>(vehicleService.addVehicle(vehicleDto), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<String> displayVehicles(@Valid @RequestBody GetVehicleDto vehicleDto) {
        return new ResponseEntity<String>(vehicleService.displayAvailableVehicles(vehicleDto), HttpStatus.OK);
    }
}
