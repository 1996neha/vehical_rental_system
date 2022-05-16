package com.example.vehicalRentalSystem.controller;

import com.example.vehicalRentalSystem.constant.VehicleType;
import com.example.vehicalRentalSystem.model.dto.AddVehicleDto;
import com.example.vehicalRentalSystem.model.dto.BranchDto;
import com.example.vehicalRentalSystem.model.dto.GetVehicleDto;
import com.example.vehicalRentalSystem.service.BranchService;
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

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BranchControllerTest {
    private ObjectMapper mapper;
    private BranchDto branchDto;
    private MockMvc mockMvc;
    @Mock
    private BranchService branchService;

    @Before
    public void init() {
        mapper = new ObjectMapper();
        BranchController branchController = new BranchController(branchService);
        mockMvc = MockMvcBuilders.standaloneSetup(branchController).build();
    }

    @Test
    public void testAddBranch_shouldReturnTrueAnd200Ok() throws Exception {
        branchDto = BranchDto.builder().id("B1").vehicleTypes(new ArrayList<String>(Arrays.asList("BUS", "BIKE"))).
                build();
        when(branchService.addBranch(branchDto)).thenReturn(Boolean.TRUE);
        MvcResult mvcResult = mockMvc.perform(post("/branches")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(branchDto)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        assertEquals(mapper.writeValueAsString(Boolean.TRUE), mvcResult.getResponse().getContentAsString());
    }
}
