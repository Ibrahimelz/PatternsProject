package org.example.model;

import lombok.*;
import org.example.controller.AirLineTicketSystemController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Employee extends User {
    @Getter
    private static Queue<Ticket> ticketsToCancel = new PriorityQueue<>();

    public Employee(String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
    }

    /**
     * When booking a ticket for a passenger, the employee will check if the entered credentials are valid.
     * If they used a discount, their credits will decrease.
     * The ticket will get be removed from the unbooked tickets list and added to the passengers tickets.
     * The available seats on the plane will decrease depending on the amount of tickets associated to the plane.
     * @param tickets   the tickets that will be booked
     * @param passenger the passenger that wants to book the ticket
     */

    public void bookTickets(Passenger passenger, List<Ticket> tickets) {
        if (AirLineTicketSystemController.findUserInSystem(passenger.getEmail()) == null) {
            throw new IllegalArgumentException("Wrong Passenger Credentials");
        }
        super.bookTickets(passenger, tickets);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "userID='" + userID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
