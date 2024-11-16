package org.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.example.controller.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class User {
    protected String userID;
    protected String firstName;
    protected String lastName;
    protected String phoneNumber;
    protected String email;
    protected String password;
    @Getter protected static int counter = 1;

    /**
     * Generates an id with a specific format
     * @return the formatted generated id
     */
    public static String generateId() {
        int id = User.getCounter();
        return String.format("000%02d", id++);
    }

    /**
     * Validates if inputted data to create an account for passenger is valid or not
     * @param firstName first name that will be validated
     * @param LastName  last name that will be validated
     * @param phoneNumber phone number that will be validated
     * @param email email that will be validated
     * @return true if all fields entered are valid and false if a field is incorrect
     */
    public boolean validateInputPassengerCreation(String firstName, String LastName, String phoneNumber, String email, String password) {
        if (!firstName.matches("[a-zA-Z]{1,50}") || !LastName.matches("[a-zA-Z]{1,50}")) {
            return false;
        }
        if (!phoneNumber.matches("\\d{10}")) {   //CHECK IF IT CONTAINS 10DIGITS ONLY: 123-456-7890 (no "-" in input)
            return false;
        }
        if (!(email.length() >= 5 && email.length() <= 254) || !email.contains("@") || !email.contains(".")) {
            return false;
        }
        if (password.length() < 5 || password.length() > 20) {
            return false;
        }
        return true;
    }

    /**
     * Finds user in the system
     * @param email the email that will be used to search for the
     * @return the user if they were found
     */
    public User findUserInSystem(String email) {
        for (Passenger passenger : AirlineTicketSystem.getPassengers()) {
            if (passenger.getEmail().equals(email)) {
                return passenger;
            }
        }

        for (Employee employee : AirlineTicketSystem.getEmployees()) {
            if (employee.getEmail().equals(email)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * When booking a ticket, the passenger will pay and their ticket will be set as their userID.
     * If they used a discount, their credits will decrease.
     * The ticket will get be removed from the unbooked tickets list and added to the passengers tickets.
     * The available seats on the plane will decrease depending on the amount of tickets associated to the plane.
     * @param tickets the tickets that will be booked
     * @param passenger the passenger that wants to book the ticket
     */
    public void bookTickets(Passenger passenger, List<Ticket> tickets) {
        payForTickets(passenger, tickets);

        for (Ticket ticket : tickets) {
            ticket.setTicketID(passenger.getUserID());
            AirlineTicketSystem.getUnbookedTickets().remove(ticket);
            removeSeatFromAirplane(ticket);
            passenger.getTicketsList().add(ticket);
        }

        double discount = calculateDiscountWithCredits(passenger.getCredits());
        passenger.setCredits(passenger.getCredits() - deductCredit(discount));
    }

    /**
     * Tickets that the passenger will pay. If they have enough credentials, their first ticket will get a discount
     * @param passenger
     * @param tickets
     * @return
     */
    public double payForTickets(Passenger passenger, List<Ticket> tickets) {
        double totalAmount = calculateNewTicketPrice(tickets.get(0), passenger.getCredits());;
        createPaymentOperation(tickets.get(0), totalAmount, passenger);

        for (int i = 1; i < tickets.size(); i++) {
            totalAmount += tickets.get(i).getPrice();
            createPaymentOperation(tickets.get(i), tickets.get(i).getPrice(), passenger);
        }
        return totalAmount;// may be used***
    }

    /**
     * Stores the payment information for a ticket by creating a payment operation
     * @param ticket provides ticketID
     * @param totalAmount amount that will be paid for the ticket
     */
    private void createPaymentOperation(Ticket ticket, double totalAmount, Passenger passenger) {
        PaymentOperation paymentOperation = new PaymentOperation("Payment Type", passenger);
        paymentOperation.getPaymentPerTicket().put(ticket.getTicketID(), totalAmount);
    }

    /**
     * After purchasing a ticket, specific number of seats gets removed from airplane available seats
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
    public static double calculateDiscountWithCredits(int passengerCredits) {
        double discount;
        if (passengerCredits < 100) {
            discount = 0.0;
        } else if (passengerCredits < 500) {
            discount = 0.05;
        } else if (passengerCredits < 1000) {
            discount = 0.1;
        } else if (passengerCredits < 2000) {
            discount = 0.15;
        } else {
            discount = 0.2;
        }
        return discount;
    }

    /**
     * Calculates the new price of the ticket after the discount
     * @param ticket initial price of the ticket
     * @param passengerCredit the input passenger credit
     * @return the new ticket price after discount
     */

    //RETURN TO PRIVATE
    public static double calculateNewTicketPrice(Ticket ticket, int passengerCredit) {
        return ticket.getPrice() - (ticket.getPrice() * calculateDiscountWithCredits(passengerCredit));

    }

    /**
     * Deducts credits from the amount of credits of a passenger after he had a discount
     * @param discount  the input discount
     */
    public int deductCredit(double discount) {
        //MAKE IT A SWITCH
        if (discount == 0.05) {
            return 500;
        } else if (discount == 0.1) {
            return 1000;
        } else if (discount == 0.15) {
            return 1500;
        } else if (discount == 0.2) {
            return 2000;
        }
        return 0;
    }
}
