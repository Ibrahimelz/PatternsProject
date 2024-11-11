package org.example.model;

import lombok.*;
import org.example.controller.AirlineTicketSystem;

import java.util.PriorityQueue;
import java.util.Queue;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee extends User {
    private static Queue<Ticket> ticketsToCancel = new PriorityQueue<>();
    private Position position;

    public Employee(String employeeID, String firstName, String lastName, String phoneNumber, String email) {
        super(employeeID, firstName, lastName, phoneNumber, email);
    }

    /**
     * Books a ticket for a passenger
     * @param ticket    the desired ticket
     * @param passenger the passenger that wants to book the ticket
     * @param employee  the employee that reserves the ticket for the passenger
     */
    public static void bookTicket(Ticket ticket, Passenger passenger, Employee employee) {
        if (!User.findUserInSystem(passenger.getUserID()) && !User.findUserInSystem(employee.getUserID())) {
            throw new IllegalArgumentException("Wrong login credentials");
        }
        if (AirlineTicketSystem.getUnbookedTickets().stream()
                .noneMatch(unbookedTicket -> unbookedTicket.equals(ticket))) {
            throw new IllegalArgumentException("This ticket is unavailable");
        }
        payTicket(passenger.getCardExpirationDate(), passenger.getCredits(), ticket.getPrice());
        ticket.setTicketID(passenger.getUserID());
        deductCredit(calculateDiscountWithCredits(passenger.getCredits()), passenger);
        AirlineTicketSystem.getUnbookedTickets().remove(ticket);
        passenger.getTicketsList().add(ticket);
    }

    /*TODO
     public static void createEmployeeAccount(Employee employee) {

     }

    public static void removeTicket(Ticket ticket){

    }

    public static void refund(Ticket ticket){

    }
    */
}
