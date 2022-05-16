package com.example.vehicalRentalSystem.executor;

import com.example.vehicalRentalSystem.model.dto.AddVehicleDto;
import com.example.vehicalRentalSystem.model.dto.BookingDto;
import com.example.vehicalRentalSystem.model.dto.BranchDto;
import com.example.vehicalRentalSystem.model.dto.GetVehicleDto;
import com.example.vehicalRentalSystem.service.impl.BookingServiceImpl;
import com.example.vehicalRentalSystem.service.impl.BranchServiceImpl;
import com.example.vehicalRentalSystem.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class AppExecutor {

    private BranchServiceImpl branchService;
    private VehicleServiceImpl vehicleService;
    private BookingServiceImpl bookingService;

    private BranchDto branchDto;
    private GetVehicleDto getVehicleDto;
    private AddVehicleDto addVehicleDto;
    private BookingDto bookingDto;

    @Autowired
    public AppExecutor(BranchServiceImpl branchService, VehicleServiceImpl vehicleService, BookingServiceImpl bookingService) {
        this.branchService = branchService;
        this.vehicleService = vehicleService;
        this.bookingService = bookingService;
    }

    public void readFileCmd(String filePath) {
        try {
            // the file to be opened for reading
            FileInputStream fis = new FileInputStream(filePath);
            Scanner sc = new Scanner(fis); // file to be scanned
            // returns true if there is another line to read
            System.out.println("Running commands from sample_input/input1.txt");
            while (sc.hasNextLine()) {
                String[] cmdArray = sc.nextLine().split(" ");
                switch (cmdArray[0]) {
                    case "ADD_BRANCH":
                        branchDto = BranchDto.builder()
                                .id(cmdArray[1])
                                .vehicleTypes(Arrays.asList(cmdArray[2].split("\\s*,\\s*")))
                                .build();
                        Boolean result = branchService.addBranch(branchDto);
                        System.out.println(result);
                        break;
                    case "ADD_VEHICLE":
                        addVehicleDto = AddVehicleDto.builder()
                                .branchId(cmdArray[1])
                                .vehicleId(cmdArray[3])
                                .vehicleType(cmdArray[2])
                                .price(Integer.parseInt(cmdArray[4]))
                                .build();
                        result = vehicleService.addVehicle(addVehicleDto);
                        System.out.println(result);
                        break;
                    case "BOOK":
                        bookingDto = BookingDto.builder()
                                .branchId(cmdArray[1])
                                .vehicleType(cmdArray[2])
                                .startTime(Integer.parseInt(cmdArray[3]))
                                .endTime(Integer.parseInt(cmdArray[4]))
                                .build();
                        Integer bresult = bookingService.addBooking(bookingDto);
                        System.out.println(bresult);
                        break;
                    case "DISPLAY_VEHICLES":
                        getVehicleDto = GetVehicleDto.builder()
                                .branchId(cmdArray[1])
                                .startTime(Integer.parseInt(cmdArray[2]))
                                .endTime(Integer.parseInt(cmdArray[3]))
                                .build();
                        String vresult = vehicleService.displayAvailableVehicles(getVehicleDto);
                        System.out.println(vresult);
                        break;
                }

            }
            System.out.println("Executed all the commands");
            sc.close(); // closes the scanner
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

}
