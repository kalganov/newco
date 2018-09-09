package com.andreitop.newco.controller;

import com.andreitop.newco.controller.TripsController;
import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.repository.TripRepository;
import com.andreitop.newco.service.TripService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AspectTest {


    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private TripService tripService;

    private TripDto tripDto;

    @Before
    public void setUp() {
        tripDto = new TripDto();
        tripDto.setId(1L);
        tripDto.setDestination("MOS");
        tripDto.setOrigin("LA");
        tripDto.setPrice(5000);
    }

    @Test
    public void tripRepositoryMethodsTest() {
        tripRepository.findAll();
        tripRepository.save(tripDto);
        tripDto.setPrice(10000);
        tripService.update(tripDto);
        tripService.delete(1L);
        tripRepository.findAll();
    }
}