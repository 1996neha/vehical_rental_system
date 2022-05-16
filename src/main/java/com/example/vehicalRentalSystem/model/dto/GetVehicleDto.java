package com.example.vehicalRentalSystem.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GetVehicleDto {
    @NotBlank
    private String branchId;

    @NotNull
    @Min(value = 1)
    @Max(value = 24)
    private Integer startTime;

    @NotNull
    @Min(value = 1)
    @Max(value = 24)
    private Integer endTime;
}
