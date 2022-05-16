package com.example.vehicalRentalSystem.service.impl;

import com.example.vehicalRentalSystem.dao.BranchRepository;
import com.example.vehicalRentalSystem.exception.BranchNotFoundException;
import com.example.vehicalRentalSystem.model.Branch;
import com.example.vehicalRentalSystem.model.BranchId;
import com.example.vehicalRentalSystem.constant.VehicleType;
import com.example.vehicalRentalSystem.model.dto.BranchDto;
import com.example.vehicalRentalSystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {
    private final BranchRepository branchRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    @Override
    public Boolean addBranch(BranchDto branchDto) {
        List<String> vehicleTypes = branchDto.getVehicleTypes();
        BranchId branchId = new BranchId();
        branchId.setId(branchDto.getId());
        Branch branch = new Branch();
        for (String type : vehicleTypes
        ) {
            branchId.setVehicleType(VehicleType.valueOf(type));
            branch.setBranchId(branchId);
            branchRepository.save(branch);
        }
        return Boolean.TRUE;
    }

    @Override
    public Branch getBranch(String id, VehicleType type) {
        return branchRepository.findById(new BranchId(id, type)).orElse(null);
//                .orElseThrow(() ->
//                new BranchNotFoundException("false"));
    }
}
