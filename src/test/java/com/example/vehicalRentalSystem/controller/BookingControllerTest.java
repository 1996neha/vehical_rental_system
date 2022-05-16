package com.example.vehicalRentalSystem.controller;

import com.example.vehicalRentalSystem.constant.VehicleType;
import com.example.vehicalRentalSystem.model.dto.BookingDto;
import com.example.vehicalRentalSystem.service.BookingService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@RunWith(MockitoJUnitRunner.class)
public class BookingControllerTest {
    private ObjectMapper mapper;
    private BookingDto bookingDto;
    private MockMvc mockMvc;
    @Mock
    private BookingService bookingService;

    @Before
    public void init() {
        mapper = new ObjectMapper();
        BookingController bookingController = new BookingController(bookingService);
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
        bookingDto = BookingDto.builder().branchId("testBranchId").vehicleType("BIKE").startTime(3).endTime(4).build();

    }

    @Test
    public void testBook_shouldReturnPriceAnd200Ok() throws Exception {
        when(bookingService.addBooking(bookingDto)).thenReturn(145);
        MvcResult mvcResult = mockMvc.perform(post("/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(bookingDto)))
                .andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(200, status);
        assertEquals(mapper.writeValueAsString(145), mvcResult.getResponse().getContentAsString());
    }

}
