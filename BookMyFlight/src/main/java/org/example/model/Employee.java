package org.example.model;

import lombok.*;
import org.example.controller.AirlineTicketSystem;
import org.example.controller.PaymentOperation;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.regex.PatternSyntaxException;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Employee extends User {
    @Getter
    private static Queue<Ticket> ticketsToCancel = new PriorityQueue<>();


    public Employee(String firstName, String lastName, String phoneNumber, String email, String password) {
        super(generateId(), firstName, lastName, phoneNumber, email, password);
    }

    /**
     * When booking a ticket for a passenger, the employee will check if the entered credentials are valid.
     * If they used a discount, their credits will decrease.
     * The ticket will get be removed from the unbooked tickets list and added to the passengers tickets.
     * The available seats on the plane will decrease depending on the amount of tickets associated to the plane.
     * @param tickets the tickets that will be booked
     * @param passenger the passenger that wants to book the ticket
     */
    @Override
    public void bookTickets(Passenger passenger, List<Ticket> tickets) {
        if (findUserInSystem(passenger.getEmail()) == null) {
            throw new IllegalArgumentException("Wrong Passenger Credentials");
        }
        payForTickets(passenger, tickets);

        for (Ticket ticket : tickets) {
            ticket.setTicketID(passenger.getUserID());
            AirlineTicketSystem.getUnbookedTickets().remove(ticket);
            removeSeatFromAirplane(ticket);
            passenger.getTicketsList().add(ticket);
        }

        double discount = calculateDiscountWithCredits(passenger.getCredits());
        int credit = deductCredit(discount);
        passenger.setCredits(passenger.getCredits() - credit);
    }


    //DO WE NEED TO MAKE THIS STATIC
     public void addEmployee(String firstName, String LastName, String phoneNumber, String email, String password) {
         if (!validateInputPassengerCreation(firstName, LastName, phoneNumber, email, password)) {
             throw new IllegalArgumentException("Passenger information provided is incorrect!");
         }
         if (findUserInSystem(email) != null) {   //CHECK IF PASSENGER HAS ALREADY AN ACCOUNT
             throw new IllegalArgumentException("Account already exists!");
         }
         Employee employee = new Employee(firstName, LastName, phoneNumber, email, password);
         AirlineTicketSystem.getEmployees().add(employee);
     }

    /**
     * remove ticket from passengers booked tickets list
     * @return ticket that has been cancelled
     */
    public Ticket removeTicket(){
        Ticket ticketCancelled = ticketsToCancel.peek();
        Passenger passengerOwner = null;
        for (Passenger passenger : AirlineTicketSystem.getPassengers()) {
            if (passenger.getUserID().equals(ticketCancelled.getPassengerID())) {
                passengerOwner = passenger;
            }
        }
        passengerOwner.getTicketsList().remove(ticketCancelled);
        passengerOwner.setCredits(passengerOwner.getCredits() + findCreditApplied(ticketCancelled));
        //TODO CREATE CANCEL OPERATION: SHOULD IT ALSO CONTAIN A MAP (TICKETID, REFUNDCREDIT) + REFUND MONEY
        return ticketCancelled;
    }

    //CREATE THE FILE I/O
    public int findCreditApplied(Ticket ticket){
        double totalAmount = 0;
        for (PaymentOperation operation : AirlineTicketSystem.getPaymentHistory()) {
            totalAmount = operation.getPaymentPerTicket().get(ticket.getTicketID());
        }
        double refundMoney = totalAmount;
        double discountUsed = ((100 * refundMoney) / ticket.getPrice()) / 100;
        return deductCredit(discountUsed);
    }

    //MOVE REFUNLIST FROM PASSENGER IN AIRLINE SYSTEM


}
