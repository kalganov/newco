package com.andreitop.newco.controller;

import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.repository.TripRepository;
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

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(TripService.class)
public class TripsServiceTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TripRepository tripRepository;
    private TripService tripService;

    private TripDto simpleTrip;

    @Before
    public void setUp() {
        tripService = new TripService(tripRepository);
        simpleTrip = new TripDto();
        simpleTrip.setId(1L);
        simpleTrip.setOrigin("MOW");
        simpleTrip.setDestination("LED");
        simpleTrip.setPrice(4232);
    }


    @Test
    public void saveTest() {
        tripService.save(simpleTrip);
        verify(tripRepository, times(1)).save(simpleTrip);
    }


    @Test
    public void findAll() {
        List<TripDto> allTrips = Collections.singletonList(simpleTrip);
        when(tripRepository.findAll()).thenReturn(allTrips);
        List<TripDto> trips = tripService.findAll();
        assertEquals(allTrips, trips);
    }

    @Test
    public void findByIdTest() {
        when(tripRepository.findById(1L)).thenReturn(simpleTrip);
        TripDto actual = tripService.findById(1L);
        assertThat(actual, is(simpleTrip));
    }

    @Test
    public void updateTest() {
        tripService.update(simpleTrip);
        verify(tripRepository, times(1)).update(simpleTrip);
    }

    @Test
    public void deleteTest() {
        tripService.delete(1L);
        verify(tripRepository, times(1)).delete(1L);
    }
}