package com.andreitop.newco.repository;

import com.andreitop.newco.dto.TripDto;
import com.andreitop.newco.exceptions.TripNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class TripRepository implements RepositoryGeneric<TripDto> {

    private final List<TripDto> trips = new ArrayList<>();

    public List<TripDto> findAll() {
        return trips;
    }

    public TripDto findById(final Long id) {
        return trips.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TripNotFoundException(id));
    }

    public void save(final TripDto trip) {
        trip.setId((long) (trips.size() + 1));
        trips.add(trip);
    }

    public void delete(final Long id) {
        trips.remove(trips.stream()
                .filter(t -> t.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new TripNotFoundException(id)));
    }

    public void update(final TripDto newTrip) {
        trips.set(trips.indexOf(trips.stream()
                .filter(t -> t.getId().equals(newTrip.getId()))
                .findFirst()
                .orElseThrow(() -> new TripNotFoundException(newTrip.getId()))), newTrip);
    }
}
