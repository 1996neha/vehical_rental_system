package com.example.vehicalRentalSystem.dao;

import com.example.vehicalRentalSystem.model.Branch;
import com.example.vehicalRentalSystem.model.BranchId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BranchRepository extends JpaRepository<Branch, BranchId> {
}
