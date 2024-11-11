package org.example.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.example.controller.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@ToString
@EqualsAndHashCode
public abstract class User {
    private String userID;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    /**
     * Finds user in the system
     * @param userID the input user id
     * @return whether the user is found or not
     */
    public static boolean findUserInSystem(String userID) {
        return AirlineTicketSystem.getPassengers().stream().anyMatch(p -> p.getUserID().equals(userID)) ||
                AirlineTicketSystem.getEmployees().stream().anyMatch(e -> e.getUserID().equals(userID));
    }

    /**
     * Pays ticket with the card of the client and client discount if he has credits
     * @param cardExpirationDate the input expiration date of the card
     * @param passengerCredits   the input passenger credits
     * @param ticketPrice        the input ticket price
     */
    public static void payTicket(String cardExpirationDate, int passengerCredits, double ticketPrice) {
        if (!validatePayment(cardExpirationDate)) {
            throw new IllegalArgumentException("Card is expired!");
        }
        double newTicketPrice = calculateNewTicketPrice(ticketPrice, passengerCredits);
        System.out.println("Thank you for your purchase! Your " + newTicketPrice + "$ ticket is confirmed.");
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
     * @param ticketPrice     the input ticket price
     * @param passengerCredit the input passenger credit
     * @return the new ticket price after discount
     */
    private static double calculateNewTicketPrice(double ticketPrice, int passengerCredit) {
        return ticketPrice - (ticketPrice * calculateDiscountWithCredits(passengerCredit));
    }

    /**
     * Ensures that the registered passenger card is still valid
     * @param cardExpirationDate the input card expiration date
     * @return whether the card is expired or not
     */
    private static boolean validatePayment(String cardExpirationDate) {
        try {
            LocalDate expirationDate = LocalDate.parse(cardExpirationDate, DateTimeFormatter.ISO_LOCAL_DATE);
            if (!expirationDate.isAfter(LocalDate.now())) {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        return true;
    }

    /**
     * Deducts credits from the amount of credits of a passenger after he had a discount
     * @param discount  the input discount
     * @param passenger the input passenger
     */
    public static void deductCredit(double discount, Passenger passenger) {
        if (discount == 0.05) {
            passenger.setCredits(passenger.getCredits() - 500);
        } else if (discount == 0.1) {
            passenger.setCredits(passenger.getCredits() - 1000);
        } else if (discount == 0.15) {
            passenger.setCredits(passenger.getCredits() - 1500);
        } else if (discount == 0.2) {
            passenger.setCredits(passenger.getCredits() - 2000);
        }
    }
}
