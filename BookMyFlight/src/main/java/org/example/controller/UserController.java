package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.*;

import java.util.List;

public class UserController {
    /**
     * Books ticket for passenger
     * If they used a discount, their credits will decrease.
     * The ticket will get be removed from the unbooked tickets list and added to the passengers tickets.
     * The available seats on the plane will decrease depending on the amount of tickets associated to the plane.
     * @param tickets the tickets that will be booked
     * @param passenger the passenger that wants to book the ticket
     */
    public void bookTickets(Passenger passenger, List<Ticket> tickets) {
        payForTickets(passenger, tickets);
        for (Ticket ticket : tickets) {
            ticket.setPassengerID(passenger.getUserID());
            AirlineTicketSystem.getInstance().getUnbookedTickets().remove(ticket);
            removeSeatFromAirplane(ticket);
            passenger.getTicketsList().add(ticket);
        }
        double discount = calculateDiscountWithCredits(passenger.getCredits());
        passenger.setCredits(passenger.getCredits() - deductCredit(discount));
    }

    /**
     * Tickets that the passenger will pay. If they have enough credentials, their first ticket will get a discount
     * @param passenger that will pay for the ticket
     * @param tickets that the passenger is booking
     * @return total amount for the booked tickets
     */
    public double payForTickets(Passenger passenger, List<Ticket> tickets) {
        double totalAmount = calculateNewTicketPrice(tickets.get(0), passenger.getCredits());
        createPaymentOperation(tickets.get(0), totalAmount, passenger);
        for (int i = 1; i < tickets.size(); i++) {
            totalAmount += tickets.get(i).getPrice();
            createPaymentOperation(tickets.get(i), tickets.get(i).getPrice(), passenger);
        }
        return totalAmount;
    }

    /**
     * Stores the payment information for a ticket by creating a payment operation
     * @param ticket provides ticketID
     * @param totalAmount amount that will be paid for the ticket
     */
    private void createPaymentOperation(Ticket ticket, double totalAmount, Passenger passenger) {
        PaymentOperation paymentOperation = new PaymentOperation(passenger, ticket);
        paymentOperation.getPaymentPerTicket().put(ticket.getTicketID(), totalAmount);
    }

    /**
     * Removes a single seat in a specific airplane
     * @param ticket containing the airplane and its seats available
     * @return number of seats left
     */
    public int removeSeatFromAirplane(Ticket ticket) {
        return ticket.getAirplane().getAvailableSeats() - 1;
    }

    /**
     * Calculates the discount on a ticket price based on the number of credits
     * @param passengerCredits the input passenger credits
     * @return the discount
     */
    public double calculateDiscountWithCredits(int passengerCredits) {
        if (passengerCredits == User.getCREDIT_FIVE_PERCENT()) {
            return 0.05;
        } else if (passengerCredits > User.getCREDIT_FIVE_PERCENT() && passengerCredits <= User.getCREDIT_TEN_PERCENT()) {
            return 0.1;
        } else if (passengerCredits > User.getCREDIT_TEN_PERCENT() && passengerCredits <= User.getCREDIT_FIFTEEN_PERCENT()) {
            return 0.15;
        } else if ((passengerCredits > User.getCREDIT_FIFTEEN_PERCENT() && passengerCredits <= User.getCREDIT_TWENTY_PERCENT())
                || passengerCredits >= User.getCREDIT_TWENTY_PERCENT()) {
            return 0.2;
        } else {
            return 0.0;
        }
    }

    /**
     * Calculates the new price of the ticket after the discount
     * @param ticket initial price of the ticket
     * @param passengerCredit the input passenger credit
     * @return the new ticket price after discount
     */
    private double calculateNewTicketPrice(Ticket ticket, int passengerCredit) {
        return ticket.getPrice() - (ticket.getPrice() * calculateDiscountWithCredits(passengerCredit));
    }

    /**
     * Deducts credits from the amount of credits of a passenger after he had a discount**WRONG**
     * @param discount the input discount
     */
    public int deductCredit(double discount) {
        return switch (String.valueOf(discount)) {
            case "0.05" -> User.getCREDIT_FIVE_PERCENT();
            case "0.1" -> User.getCREDIT_TEN_PERCENT();
            case "0.15" -> User.getCREDIT_FIFTEEN_PERCENT();
            case "0.2" -> User.getCREDIT_TWENTY_PERCENT();
            default -> 0;
        };
    }
}
