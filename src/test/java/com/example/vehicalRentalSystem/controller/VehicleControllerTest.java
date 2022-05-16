package com.example.vehicalRentalSystem.controller;

import com.example.vehicalRentalSystem.model.dto.AddVehicleDto;
import com.example.vehicalRentalSystem.model.dto.BookingDto;
import com.example.vehicalRentalSystem.model.dto.GetVehicleDto;
import com.example.vehicalRentalSystem.service.VehicleService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.Silent.class)
public class VehicleControllerTest {
    private ObjectMapper mapper;
    private GetVehicleDto getVehicleDto;
    private AddVehicleDto addVehicleDto;
    private MockMvc mockMvc;
    @Mock
    private VehicleService vehicleService;

    @Before
    public void init() {
        mapper = new ObjectMapper();
        VehicleController vehicleController = new VehicleController(vehicleService);
        mockMvc = MockMvcBuilders.standaloneSetup(vehicleController).build();
    }

    @Test
    public void testAddVehicle_shouldReturnTrueAnd200Ok() throws Exception {
        addVehicleDto = AddVehicleDto.builder().branchId("test").vehicleId("V1").vehicleType("CAR").price(100).build();
        when(vehicleService.addVehicle(addVehicleDto)).thenReturn(Boolean.TRUE);
        MvcResult mvcResult = mockMvc.perform(post("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(addVehicleDto)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals(mapper.writeValueAsString(Boolean.TRUE), mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void testDisplayVehicles_shouldReturnStringAnd200Ok() throws Exception {
        getVehicleDto = GetVehicleDto.builder().branchId("test").startTime(3).endTime(4).build();
        when(vehicleService.displayAvailableVehicles(getVehicleDto)).thenReturn("V1,V2");
        MvcResult mvcResult = mockMvc.perform(get("/vehicles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(getVehicleDto)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals("V1,V2", mvcResult.getResponse().getContentAsString());
    }


}
