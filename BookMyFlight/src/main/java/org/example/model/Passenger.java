package org.example.model;

import lombok.*;
import org.example.controller.AirlineTicketSystem;
import org.example.controller.CancelOperation;
import org.example.controller.Operation;
import org.example.controller.PaymentOperation;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Passenger extends User {
    @Getter private int credits;
    @Getter private int cardNumber;
    @Getter private String cardHolderName;
    @Getter private String cardExpirationDate;
    @Getter private int cvc;
    @Getter private List<Ticket> ticketsList;
    @Getter private List<CancelOperation> refundList;

    public Passenger( String firstName, String lastName, String phoneNumber, String email, String password) {
        //passengerID becomes the generateID?
        super(generateId(), firstName, lastName, phoneNumber, email, password);
        this.cardNumber = 0;
        this.cardHolderName = null;
        this.cardExpirationDate = null;
        this.cvc = 0;
        this.credits = 0;
        this.ticketsList = new LinkedList<>();
        this.refundList = new LinkedList<>();
    }


    /**
     * Validates, Creates a regular passenger account and adds it to the passenger list
     * @param firstName first name entered by the passenger
     * @param LastName  last name first entered by the passenger
     * @param phoneNumber   phone number first entered by the passenger
     * @param email email entered by the passenger
     * @param password password entered by the passenger
     */
    //SHOULD I MAKE IT RETURN A BOOLEAN INSTEAD? IF TRUE = BUTTON APPEARS
    public void addRegularPassenger(String firstName, String LastName, String phoneNumber, String email, String password) {
        if (!validateInputPassengerCreation(firstName, LastName, phoneNumber, email, password)) {
            throw new IllegalArgumentException("Passenger information provided is incorrect!");
        }
        if (findUserInSystem(email) != null) {   //CHECK IF PASSENGER HAS ALREADY AN ACCOUNT
            throw new IllegalArgumentException("Account already exists!");
        }
        Passenger passenger = new Regular(firstName, lastName, phoneNumber, email, password);
        AirlineTicketSystem.getPassengers().add(passenger);
    }

    /**
     * Validate card input from the passenger
     * @param cardNumber card number that they have entered
     * @param cardHolderName card name that they have entered
     * @param cardExpirationDate card expiration date
     * @param cvc
     * @return
     */
    public boolean validatePaymentInput(String cardNumber,String cardHolderName, String cardExpirationDate, String cvc) {
        LocalDate present = LocalDate.now();
        if ((!cardNumber.matches("\\d{13,19}"))) {
            return false;
        }
        if (!cardHolderName.matches("[a-zA-Z]+( [a-zA-Z]+)*")) {    //verifies if only letter and can have spaces between strings
            return false;
        }
        if (!cardExpirationDate.matches("^(0[1-9]|1[0-2])/\\d{2}$")) {
            return false;
        }
        if (cvc.matches("\\d{100,999}")) {
            return false;
        }
        if (!verifyExpirationDate(cardExpirationDate)) {
            return false;
        }
        return true;
    }

    /**
     * Verify if there are seats available
     * @param ticket containing the airplane and its seats available
     */
    public void verifySeatsAvailable(Ticket ticket) {
        if (ticket.getAirplane().getAvailableSeats() == 0) {
            throw new IllegalArgumentException("No available seats!");
        }
    }


    /**
     * When requesting a refund, the passenger will send a request to an employee, along with their ticket information
     * @param ticket that will be refunded
     */
    public void requestRefund(Ticket ticket){
        Employee.getTicketsToCancel().add(ticket);
    }

    /**
     * Ensures that the registered passenger card is still valid
     * @param cardExpirationDate the input card expiration date
     * @return whether the card is expired or not
     */
    private boolean verifyExpirationDate(String cardExpirationDate) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yy");
            LocalDate date = LocalDate.parse(cardExpirationDate, format);
            if (!date.isAfter(LocalDate.now())) {
                return false;
            }
        } catch (DateTimeParseException e) {
            return false;
        }
            return true;
    }
}
