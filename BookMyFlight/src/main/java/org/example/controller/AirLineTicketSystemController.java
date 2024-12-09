package org.example.controller;

import org.example.model.*;

public class AirLineTicketSystemController {
    private AirlineTicketSystem airlineTicketSystem;

    public AirLineTicketSystemController() {
        initSystem();
        this.airlineTicketSystem = AirlineTicketSystem.getInstance();
    }

    /**
     * Initializes the system by making sure all the tables in the database are created
     */
    public static void initSystem() {
        DatabaseController.createTableEmployee();
        DatabaseController.createTablePassenger();
        DatabaseController.createTableAirplane();
        DatabaseController.createTableOperation();
        DatabaseController.createTableTicket();
    }

    /**
     * Finds user in the system
     * @param email the email that will be used to search for the user
     * @return the user if they were found
     */
    public static User findUserInSystem(String email) {
        for (Passenger passenger : AirlineTicketSystem.getInstance().getPassengers()) {
            if (passenger.getEmail().equals(email)) {
                return passenger;
            }
        }
        for (Employee employee : AirlineTicketSystem.getInstance().getEmployees()) {
            if (employee.getEmail().equals(email)) {
                return employee;
            }
        }
        return null;
    }

    /**
     * Find a ticking depending on the ticket
     * @param ticketId the input ticketId
     * @return return the ticket if it is found
     */
    public static Ticket findTicket(String ticketId) {
        for (Ticket ticket : DatabaseController.queryTicketsAll()) {
            if (ticket.getTicketID().equals(ticketId)) {
                return ticket;
            }
        }
        return null;
    }

    /**
     * Update the passenger card information when the employee or passenger fill in the card information
     * @param passenger that will be updated
     * @param cardNum card number entered
     * @param cardHolder card holder name entered
     * @param expDate expiration date of the card entered
     * @param cvc cvc of the card entered
     */
    public static void updatePassengerCardInfo(Passenger passenger, String cardNum, String cardHolder, String expDate, int cvc) {
        passenger.setCardExpirationDate(expDate);
        passenger.setCardNumber(cardNum);
        passenger.setCardHolderName(cardHolder);
        passenger.setCvc(cvc);
    }
}
