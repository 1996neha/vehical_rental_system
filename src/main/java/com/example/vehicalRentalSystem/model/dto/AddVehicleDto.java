package com.example.vehicalRentalSystem.model.dto;

import com.example.vehicalRentalSystem.constant.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AddVehicleDto {
    @NotBlank
    private String branchId;
    @NotNull
    private String vehicleType;
    @NotBlank
    private String vehicleId;
    @NotNull
    private Integer price;
}
