package com.indra.StaySmart.customException;

public class HotelNotFoundException extends Exception {

    public HotelNotFoundException(String message) {
        super(message);
    }
}
