package com.indra.StaySmart.customException;

public class ResourceNotFoundException extends Exception {

    public ResourceNotFoundException(String hotelNotFound) {
        super(hotelNotFound);
    }
}
