package com.andreitop.newco.controller;

import com.andreitop.newco.common.ApiConstant;
import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.service.ServiceGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(ApiConstant.API_V_1 + "/trips")
public class TripsController implements ControllerGeneric<TripDto> {

    private final ServiceGeneric<TripDto> tripService;

    @Autowired
    public TripsController(ServiceGeneric<TripDto> tripService) {
        this.tripService = tripService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TripDto> findAll() {
        return tripService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TripDto findById(@PathVariable("id") final Long id) {
        return tripService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody final TripDto trip) {
        tripService.save(trip);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") final Long id) {
        tripService.delete(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void update(@RequestBody final TripDto newTrip) {
        tripService.update(newTrip);
    }
}
