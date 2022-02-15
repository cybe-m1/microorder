package com.microorders.customer.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class CustomerPhoneNumberTakenException extends Exception {
    public CustomerPhoneNumberTakenException() {
    }

    public CustomerPhoneNumberTakenException(String message) {
        super(message);
    }
}
