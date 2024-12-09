package org.example.model;

import lombok.*;
import org.example.controller.DatabaseController;

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
    @Getter
    private List<Airplane> airplanes;

    private AirlineTicketSystem() {
        this.unbookedTickets = DatabaseController.queryUnbookedTicketsAll();
        this.paymentHistory = DatabaseController.queryAllPaymentOperations();
        this.passengers = DatabaseController.queryPassengerAll();
        this.employees = DatabaseController.queryEmployeeAll();
        this.airplanes = DatabaseController.queryAirplaneAll();
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
