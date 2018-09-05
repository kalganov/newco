package com.andreitop.newco.service;

import java.util.List;

public interface ServiceGeneric<T> {
    List<T> findAll();

    T findById(Long id);

    void save(T trip);

    void delete(Long id);

    void update(T newTrip);
}
