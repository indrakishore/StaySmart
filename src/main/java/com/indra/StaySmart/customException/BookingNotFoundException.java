package com.indra.StaySmart.customException;

import org.springframework.data.crossstore.ChangeSetPersister;

public class BookingNotFoundException extends ChangeSetPersister.NotFoundException {
    public BookingNotFoundException(String s) {
        super();
    }
}
