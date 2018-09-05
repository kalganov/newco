package com.andreitop.newco.controller;

import com.andreitop.newco.common.ApiConstant;
import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.service.TripService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TripsController.class)
public class TripsControllerTest {

    private static final String TRIP_JSON = "{\"origin\": \"LED\" , \"destination\":\"MOW\", \"price\" : 12256}";
    private static final String CONTENT_TYPE = "application/json;charset=UTF-8";
    private static final String API_URL = ApiConstant.API_V_1 + "/trips";
    private static final String API_URL_WITH_ID = ApiConstant.API_V_1 + "/trips/{id}";

    private TripDto simpleTrip;

    @Before
    public void setUp() {
        simpleTrip = new TripDto();
        simpleTrip.setId(1L);
        simpleTrip.setOrigin("MOW");
        simpleTrip.setDestination("LED");
        simpleTrip.setPrice(4232);
    }

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripService tripService;

    @Test
    public void whenPostTrip_thenCreateTrip() throws Exception {
        mockMvc.perform(post(API_URL)
                .contentType(CONTENT_TYPE)
                .content(TRIP_JSON))
                .andExpect(status().isCreated());
    }

    @Test
    public void givenTrips_whenGetTrips_thenReturnJsonArray() throws Exception {
        List<TripDto> allTrips = Collections.singletonList(simpleTrip);
        given(tripService.findAll()).willReturn(allTrips);

        mockMvc.perform(get(API_URL))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[0].origin", is("MOW")))
                .andExpect(jsonPath("$[0].destination", is("LED")))
                .andExpect(jsonPath("$[0].price", is(4232)));
    }

    @Test
    public void findByIdTest() throws Exception {
        when(tripService.findById(1L)).thenReturn(simpleTrip);

        mockMvc.perform(get(API_URL_WITH_ID, 1L))
                .andExpect(status().isOk())
                .andExpect(content().contentType(CONTENT_TYPE))
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.origin", is("MOW")))
                .andExpect(jsonPath("$.destination", is("LED")))
                .andExpect(jsonPath("$.price", is(4232)));
    }

    @Test
    public void updateTest() throws Exception {
        mockMvc.perform(put(API_URL)
                .contentType(CONTENT_TYPE)
                .content(TRIP_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void deleteTest() throws Exception {
        mockMvc.perform(delete(API_URL_WITH_ID, 1L)
                .contentType(CONTENT_TYPE))
                .andExpect(status().isNoContent());
    }
}