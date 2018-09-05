package com.andreitop.newco.exceptions;

public class TripNotFoundException extends RuntimeException {
    private String message;

    public TripNotFoundException(long id) {
        message = "Trip with id = " + id + " doesn't exist";
    }

    public String getMessage() {
        return message;
    }
}