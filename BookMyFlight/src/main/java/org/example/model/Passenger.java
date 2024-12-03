package org.example.model;

import lombok.*;
import org.example.controller.DatabaseController;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor

public abstract class Passenger extends User {
    @Getter
    protected int credits;
    @Getter
    protected String cardNumber;
    @Getter
    protected String cardHolderName;
    @Getter
    protected String cardExpirationDate;
    @Getter
    protected int cvc;
    @Getter
    protected List<Ticket> ticketsList;
    @Getter
    protected List<CancelOperation> refundList;


    public Passenger(String firstName, String lastName, String phoneNumber, String email, String password) {
        super( firstName, lastName, phoneNumber, email, password);
        this.cardNumber = null;
        this.cardHolderName = null;
        this.cardExpirationDate = null;
        this.cvc = 0;
        this.credits = 0;
        this.ticketsList = DatabaseController.queryUnbookedTicketsAll();
        this.refundList = DatabaseController.queryAllCancelOperations();
    }

    /**
     * Validates card input from the passenger
     * @param cardNumber         card number that they have entered
     * @param cardHolderName     card name that they have entered
     * @param cardExpirationDate card expiration date that they have entered
     * @param cvc                cvc that they have entered
     * @return true if their input is valid
     */
    public static boolean validatePaymentInput(String cardNumber, String cardHolderName, String cardExpirationDate, String cvc) {
        LocalDate present = LocalDate.now();
        if ((!cardNumber.matches("\\d{13,19}"))) {
            return false;
        }
        if (!cardHolderName.matches("[a-zA-Z]+( [a-zA-Z]+)*")) {
            return false;
        }
        if (!cardExpirationDate.matches("^(0[1-9]|1[0-2])/\\d{2}$")) {
            return false;
        }
        if (!cvc.matches("\\d{3,4}")) {
            return false;
        }
        if (isCardExpired(cardExpirationDate)) {
            return false;
        }
        return true;
    }

    /**
     * Verifies if there are seats available
     * @param ticket containing the airplane and its seats available
     */
    public void verifySeatsAvailable(Ticket ticket) {
        if (ticket.getAirplane().getAvailableSeats() == 0) {
            throw new IllegalArgumentException("No available seats!");
        }
    }

    /**
     * Verifies registered passenger card is still valid
     * @param cardExpirationDate the input card expiration date
     * @return true if the card is expired and false if the card is not expired
     */
    public static boolean isCardExpired(String cardExpirationDate) {
        try {
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/yy");
            YearMonth expiry = YearMonth.parse(cardExpirationDate, format);
            return expiry.isBefore(YearMonth.now());
        } catch (DateTimeParseException e) {
            return true;
        }
    }
}
