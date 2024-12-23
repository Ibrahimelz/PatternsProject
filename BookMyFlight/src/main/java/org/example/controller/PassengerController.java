package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.*;

@AllArgsConstructor
public class PassengerController {
    private Passenger passenger;

    /**
     * Creates regular passenger and adds them to the passenger list
     * @param firstName first name entered by the passenger
     * @param LastName last name first entered by the passenger
     * @param phoneNumber phone number first entered by the passenger
     * @param email email entered by the passenger
     * @param password password entered by the passenger
     * @return return true if the passenger was successfully created
     */
    public static boolean addRegularPassenger(String firstName, String LastName, String phoneNumber, String email, String password) {
        if (!Passenger.validateInputUserCreation(firstName, LastName, phoneNumber, email, password)) {
            throw new IllegalArgumentException("User information provided is incorrect!");
        }
        if (AirLineTicketSystemController.findUserInSystem(email) != null) {
            throw new IllegalArgumentException("Account already exists!");
        }
        Passenger passenger = new Regular(firstName, LastName, phoneNumber, email, password);
        DatabaseController.insertPassengerRecord(passenger);
        return true;
    }

    /**
     * Requests ticket cancellation
     * @param ticket that will be cancelled and refunded
     */
    public void requestRefund(Ticket ticket) {
        Employee.getTicketsToCancel().add(ticket);
    }
}
