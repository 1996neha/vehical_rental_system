package com.example.vehicalRentalSystem.dao;

import com.example.vehicalRentalSystem.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VehicleRepository extends JpaRepository<Vehicle,String> {
    public List<Vehicle> findAllByOrderByPriceAsc();
}
