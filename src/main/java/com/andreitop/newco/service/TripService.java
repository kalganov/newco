package com.andreitop.newco.service;

import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.repository.RepositoryGeneric;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TripService implements ServiceGeneric<TripDto> {

    private final RepositoryGeneric<TripDto> tripRepository;

    @Autowired
    public TripService(RepositoryGeneric<TripDto> tripRepository) {
        this.tripRepository = tripRepository;
    }

    public List<TripDto> findAll() {
        return tripRepository.findAll();
    }

    public TripDto findById(Long id) {
        return tripRepository.findById(id);
    }

    public void save(TripDto trip) {
        tripRepository.save(trip);
    }

    public void delete(Long id) {
        tripRepository.delete(id);
    }

    public void update(TripDto newTrip) {
        tripRepository.update(newTrip);
    }
}
