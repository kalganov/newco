package com.andreitop.newco.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ControllerGeneric<T> {
    List<T> findAll();

    T findById(@PathVariable("id") final Long id);

    void create(@RequestBody final T trip);

    void delete(@PathVariable("id") final Long id);

    void update(@RequestBody final T newTrip);
}
