package com.example.vehicalRentalSystem.model.dto;

import com.example.vehicalRentalSystem.constant.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDto {
    @NotBlank
    private String branchId;

    @NotNull
    private String vehicleType;

    @NotNull
    @Min(value = 1)
    @Max(value = 24)
    private Integer startTime;
    
    @NotNull
    @Min(value = 1)
    @Max(value = 24)
    private Integer endTime;
}
