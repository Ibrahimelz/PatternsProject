package org.example.model;

import java.util.List;

import lombok.*;
import org.example.controller.UserController;


@Setter
@ToString
@EqualsAndHashCode
public abstract class User {
    @Getter
    protected String userID;
    @Getter
    protected String firstName;
    @Getter
    protected String lastName;
    @Getter
    protected String phoneNumber;
    @Getter
    protected String email;
    @Getter
    protected String password;
    @Getter
    private final int CREDIT_AMOUNT_1 = 500;
    @Getter
    private final int CREDIT_AMOUNT_2 = 1000;
    @Getter
    private final int CREDIT_AMOUNT_3 = 1500;
    @Getter
    private final int CREDIT_AMOUNT_4 = 2000;
    @Getter
    private final double DISCOUNT_AMOUNT_1 = 0.05;
    @Getter
    private final double DISCOUNT_AMOUNT_2 = 0.1;
    @Getter
    private final double DISCOUNT_AMOUNT_3 = 0.15;
    @Getter
    private final double DISCOUNT_AMOUNT_4 = 0.2;
    @Getter
    protected static int counter = 1;

    public User(String userID, String firstName, String lastName, String phoneNumber, String email, String password) {
        this.userID = userID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.password = password;
    }

    /**
     * Generates an id with a specific format
     * @return the formatted generated id
     */
    protected static String generateId() {
        int id = User.getCounter();
        return String.format("000%02d", id++);
    }

    /**
     * Validates if inputted data to create an account for passenger is valid or not
     * @param firstName first name that will be validated
     * @param LastName last name that will be validated
     * @param phoneNumber phone number that will be validated
     * @param email email that will be validated
     * @return true if all fields entered are valid and false if a field is incorrect
     */
    public boolean validateInputUserCreation(String firstName, String LastName, String phoneNumber, String email, String password) {
        int minPassWordLen = 5;
        int MaxPassWordLen = 20;
        int emailMinLen = 5;
        int emailMaxLen = 255;

        if (!firstName.matches("[a-zA-Z]{1,50}") || !LastName.matches("[a-zA-Z]{1,50}")) {
            return false;
        }
        if (!phoneNumber.matches("\\d{10}")) {
            return false;
        }
        if (!(email.length() >= emailMinLen && email.length() < emailMaxLen) || !email.contains("@") || !email.contains(".")) {
            return false;
        }
        if (password.length() < minPassWordLen || password.length() > MaxPassWordLen) {
            return false;
        }
        return true;
    }

    /**
     * Books ticket for passenger
     * If they used a discount, their credits will decrease.
     * The ticket will get be removed from the unbooked tickets list and added to the passengers tickets.
     * The available seats on the plane will decrease depending on the amount of tickets associated to the plane.
     * @param tickets   the tickets that will be booked
     * @param passenger the passenger that wants to book the ticket
     */
    public void bookTickets(Passenger passenger, List<Ticket> tickets) {
        UserController userController = new UserController();
        userController.payForTickets(passenger, tickets);
        for (Ticket ticket : tickets) {
            ticket.setTicketID(passenger.getUserID());
            AirlineTicketSystem.getInstance().getUnbookedTickets().remove(ticket);
            userController.removeSeatFromAirplane(ticket);
            passenger.getTicketsList().add(ticket);
        }
        double discount = userController.calculateDiscountWithCredits(passenger.getCredits());
        passenger.setCredits(passenger.getCredits() - userController.deductCredit(discount));
    }

}
