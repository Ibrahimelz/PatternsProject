package org.example.controller;

import lombok.*;
import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.Ticket;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public class AirlineTicketSystem {
    private static List<Ticket> unbookedTickets = new LinkedList<>();
    private static List<Ticket> paymentHistory = new ArrayList<>();
    private static List<Passenger> passengers = new ArrayList<>();
    private static List<Employee> employees = new ArrayList<>();

}
