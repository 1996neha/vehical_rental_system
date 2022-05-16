package com.example.vehicalRentalSystem.model.dto;

import com.example.vehicalRentalSystem.constant.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BranchDto {
    @NotBlank
    private String id;
    @NotEmpty
    private List<String> vehicleTypes;
}
