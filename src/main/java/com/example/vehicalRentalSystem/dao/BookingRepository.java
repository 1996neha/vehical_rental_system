package com.example.vehicalRentalSystem.dao;

import com.example.vehicalRentalSystem.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Integer> {
    public List<Booking> findAllByVehicleId(String id);
    public long countDistinctVehicleIdByBranchId(String id);

}
