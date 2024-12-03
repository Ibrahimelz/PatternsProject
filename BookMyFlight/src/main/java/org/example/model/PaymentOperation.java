package org.example.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PaymentOperation extends Operation {
    @Getter
    private Map<String, Double> paymentPerTicket;

    public PaymentOperation(Passenger passenger, Ticket ticket) {
        super("Payment Operation", LocalDateTime.now(), ticket.getTicketID(), passenger.getUserID());
        this.paymentPerTicket = new HashMap<>();
    }
}
