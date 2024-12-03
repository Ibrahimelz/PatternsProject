package org.example.controller;

import org.example.model.AirlineTicketSystem;
import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.User;

public class AirLineTicketSystemController {
    private AirlineTicketSystem airlineTicketSystem;

    public AirLineTicketSystemController() {
        this.airlineTicketSystem = AirlineTicketSystem.getInstance();
        initSystem();
    }

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

}
