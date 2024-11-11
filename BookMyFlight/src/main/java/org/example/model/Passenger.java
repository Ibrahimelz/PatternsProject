package org.example.model;

import lombok.*;
import org.example.controller.AirlineTicketSystem;
import org.example.controller.CancelOperation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.LinkedList;
import java.util.List;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Passenger extends User {
    private int credits;
    private int cardNumber;
    private String cardHolderName;
    private String cardExpirationDate;
    private int cvc;
    private List<Ticket> ticketsList;
    private List<CancelOperation> refundList;
    private static int counter = 1;

    public Passenger(String passengerID, String firstName, String lastName, String phoneNumber, String email,
                     int cardNumber, String cardHolderName, String cardExpirationDate, int cvc) {
        super(passengerID, firstName, lastName, phoneNumber, email);
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.cardExpirationDate = cardExpirationDate;
        this.cvc = cvc;
        this.credits = 0;
        this.ticketsList = new LinkedList<>();
        this.refundList = new LinkedList<>();
    }

    /**
     * Creates an account for a passenger and adds him into the system
     * @param passenger the input passenger
     */
    public static void createPassengerAccount(Passenger passenger) {
        passenger.setUserID(generateId());
        if (!validateInputPassengerCreation(passenger)) {
            throw new IllegalArgumentException("Passenger information provided is incorrect");
        }
        AirlineTicketSystem.getPassengers().add(passenger);
    }

    /**
     * Generates an id with a specific format
     * @return the formatted generated id
     */
    private static String generateId() {
        counter++;
        return String.format("000%02d", counter);
    }

    /**
     * Validates input information for creation of a Passenger and ensures that all conditions are checked individually
     * @param passenger the input passenger
     * @return validation of the passenger information
     */
    private static boolean validateInputPassengerCreation(Passenger passenger) {
        if (!passenger.getFirstName().matches("[a-zA-Z]{1,50}") || !passenger.getLastName().matches("[a-zA-Z]{1,50}")) {
            return false;
        }
        if (!passenger.getPhoneNumber().matches("\\d{1,15}")) {
            return false;
        }
        if (passenger.getEmail().length() > 255 || !passenger.getEmail().contains("@") || !passenger.getEmail().contains(".")) {
            return false;
        }
        String cardNumberStr = String.valueOf(passenger.getCardNumber());
        if (!cardNumberStr.matches("\\d{13,19}")) {
            return false;
        }
        if (!passenger.getCardHolderName().matches("[a-zA-Z ]{1,50}")) {
            return false;
        }
        if (!passenger.getCardExpirationDate().matches("\\d{4}-\\d{2}-\\d{2}")) {
            return false;
        }
        try { // Validate cardExpirationDate: ensure format is YYYY-MM-DD and date is in the future
            LocalDate expirationDate = LocalDate.parse(passenger.getCardExpirationDate(), DateTimeFormatter.ISO_LOCAL_DATE);
            if (!expirationDate.isAfter(LocalDate.now())) {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
        if (passenger.getCvc() < 100 || passenger.getCvc() > 999) {
            return false;
        }
        return true;
    }

    /**
     * Books a ticket for a passenger
     * @param ticket the desired ticket
     * @param passenger the passenger that wants to book the ticket
     */
    public static void bookTicket(Ticket ticket, Passenger passenger) {
        if (!User.findUserInSystem(passenger.getUserID())) {
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

    /* TODO
    public static void requestRefund(int ticketID){

    }
    */
}
