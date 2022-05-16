package com.example.vehicalRentalSystem.service;

import com.example.vehicalRentalSystem.constant.VehicleType;
import com.example.vehicalRentalSystem.model.Branch;
import com.example.vehicalRentalSystem.model.dto.BranchDto;

public interface BranchService {
    public Boolean addBranch(BranchDto branchDto);

    public Branch getBranch(String id, VehicleType type);
}
