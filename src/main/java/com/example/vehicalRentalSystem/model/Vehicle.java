package com.example.vehicalRentalSystem.model;

import com.example.vehicalRentalSystem.constant.VehicleStatus;
import com.example.vehicalRentalSystem.constant.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "vehicle")
public class Vehicle {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private VehicleType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "booking_status")
    private VehicleStatus status;

    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "branch_id", referencedColumnName = "id"),
            @JoinColumn(name = "vehicle_type", referencedColumnName = "vehicle_type")
    })
    private Branch branch;

    private Integer price;
}
