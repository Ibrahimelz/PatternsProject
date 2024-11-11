package org.example.controller;

import lombok.*;
import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.Ticket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
@ToString
@EqualsAndHashCode
public class AirlineTicketSystem {
    private static AirlineTicketSystem instance;
    @Getter
    private static List<Ticket> unbookedTickets;
    @Getter
    private static List<Ticket> paymentHistory;
    @Getter
    private static List<Passenger> passengers;
    @Getter
    private static List<Employee> employees;   //TODO should I leave the constructor empty and put the instantiation here for each list?

    private AirlineTicketSystem() {
        this.unbookedTickets = new LinkedList<>();
        this.paymentHistory = new ArrayList<>();
        this.passengers = new ArrayList<>();
        this.employees = new ArrayList<>();
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
