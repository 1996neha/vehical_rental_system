package com.example.vehicalRentalSystem.controller;

import com.example.vehicalRentalSystem.model.dto.BranchDto;
import com.example.vehicalRentalSystem.service.BranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/branches")
public class BranchController {
    private final BranchService branchService;

    @Autowired
    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    @PostMapping
    public ResponseEntity<Boolean> addBranch(@Valid @RequestBody BranchDto branchDto) {
        return new ResponseEntity<Boolean>(branchService.addBranch(branchDto), HttpStatus.CREATED);
    }
}
