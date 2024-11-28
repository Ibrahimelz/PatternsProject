package org.example.controller;

import org.example.model.Passenger;
import org.example.model.PaymentOperation;
import org.example.model.Ticket;
import org.example.model.User;

import java.util.List;

public class UserController {
    private User user;

    /**
     * Tickets that the passenger will pay. If they have enough credentials, their first ticket will get a discount
     * @param passenger that will pay for the ticket
     * @param tickets   that the passenger is booking
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
     * @param ticket      provides ticketID
     * @param totalAmount amount that will be paid for the ticket
     */
    private void createPaymentOperation(Ticket ticket, double totalAmount, Passenger passenger) {
        PaymentOperation paymentOperation = new PaymentOperation(passenger);
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
        if (passengerCredits < user.getCREDIT_AMOUNT_1()) {
            return user.getDISCOUNT_AMOUNT_1();
        } else if (passengerCredits < user.getCREDIT_AMOUNT_2()) {
            return user.getDISCOUNT_AMOUNT_2();
        } else if (passengerCredits < user.getCREDIT_AMOUNT_3()) {
            return user.getDISCOUNT_AMOUNT_3();
        } else if (passengerCredits < user.getCREDIT_AMOUNT_4()) {
            return user.getDISCOUNT_AMOUNT_4();
        } else {
            return 0.0;
        }
    }

    /**
     * Calculates the new price of the ticket after the discount
     * @param ticket          initial price of the ticket
     * @param passengerCredit the input passenger credit
     * @return the new ticket price after discount
     */
    private double calculateNewTicketPrice(Ticket ticket, int passengerCredit) {
        return ticket.getPrice() - (ticket.getPrice() * calculateDiscountWithCredits(passengerCredit));
    }

    /**
     * Deducts credits from the amount of credits of a passenger after he had a discount
     * @param discount the input discount
     */
    public int deductCredit(double discount) {
        return switch (String.valueOf(discount)) {
            case "0.05" -> user.getCREDIT_AMOUNT_1();
            case "0.1" -> user.getCREDIT_AMOUNT_2();
            case "0.15" -> user.getCREDIT_AMOUNT_3();
            case "0.2" -> user.getCREDIT_AMOUNT_4();
            default -> 0;
        };
    }

}
