package org.example.model;

import lombok.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@ToString
@EqualsAndHashCode
public class AirlineTicketSystem {
    @Getter
    private static AirlineTicketSystem instance;
    @Getter
    private List<Ticket> unbookedTickets;
    @Getter
    private List<PaymentOperation> paymentHistory;
    @Getter
    private List<Passenger> passengers;
    @Getter
    private List<Employee> employees;

    private AirlineTicketSystem() {
        this.unbookedTickets = new LinkedList<>(); //todo
        this.paymentHistory = new ArrayList<>(); //todo
        this.passengers = new ArrayList<>(); //todo
        this.employees = new ArrayList<>(); //todo
    }

    public static AirlineTicketSystem getInstance() {
        if (instance == null) {
            synchronized (AirlineTicketSystem.class) {
                if (instance == null) {
                    instance = new AirlineTicketSystem();
                }
            }
        }
        return instance;
    }
}
