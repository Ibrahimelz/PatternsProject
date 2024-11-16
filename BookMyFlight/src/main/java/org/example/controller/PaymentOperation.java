package org.example.controller;

import lombok.*;
import org.example.model.Passenger;
import org.example.model.Ticket;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@ToString
public class PaymentOperation extends Operation {
    @Getter private Map<String, Double> paymentPerTicket;

    public PaymentOperation(String type, Passenger passenger) {
        super(type, LocalDateTime.now(), passenger.getUserID());
        this.paymentPerTicket = new HashMap<>();
    }

}
