package org.example.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
public class PaymentOperation extends Operation {
    @Getter
    private Map<String, Double> paymentPerTicket;

    public PaymentOperation(Passenger passenger) {
        super("Payment Operation", LocalDateTime.now(), passenger.getUserID());
        this.paymentPerTicket = new HashMap<>();
    }
}
