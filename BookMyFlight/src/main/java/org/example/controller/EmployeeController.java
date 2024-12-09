package org.example.controller;

import lombok.AllArgsConstructor;
import org.example.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@AllArgsConstructor
public class EmployeeController {

    /**
     * When booking a ticket for a passenger, the employee will check if the entered credentials are valid.
     * If they used a discount, their credits will decrease.
     * The ticket will get be removed from the unbooked tickets list and added to the passengers tickets.
     * The available seats on the plane will decrease depending on the amount of tickets associated to the plane.
     * @param tickets  the tickets that will be booked
     * @param passenger the passenger that wants to book the ticket
     */
    public static void bookTickets(Passenger passenger, List<Ticket> tickets) {
        if (AirLineTicketSystemController.findUserInSystem(passenger.getEmail()) == null) {
            throw new IllegalArgumentException("Wrong Passenger Credentials");
        }
        EmployeeController.bookTickets(passenger, tickets);
    }

    /**
     * Adds an employee after their inputted data is validated
     * @param firstName of the employee
     * @param LastName of the employee
     * @param phoneNumber of the employee
     * @param email of the employee
     * @param password of the employee
     */
    public static boolean addEmployee(String firstName, String LastName, String phoneNumber, String email, String password) {
        if (!Employee.validateInputUserCreation(firstName, LastName, phoneNumber, email, password)) {
            throw new IllegalArgumentException("User information provided is incorrect!");
        }
        if (AirLineTicketSystemController.findUserInSystem(email) != null) {
            throw new IllegalArgumentException("Account already exists!");
        }
        Employee employee = new Employee(firstName, LastName, phoneNumber, email, password);
        DatabaseController.insertEmployeeRecord(employee);
        return true;
    }

    /**
     * Removes ticket from passengers booked tick
     * @return ticket that has been cancelled
     */
    public static void removeTicket() {
        Ticket ticketCancelled = Employee.getTicketsToCancel().peek();
        Passenger passengerOwner = null;
        if (Employee.getTicketsToCancel().size() == 0) {
            return;
        }
        for (Passenger passenger : AirlineTicketSystem.getInstance().getPassengers()) {
            if (passenger.getUserID().equals(ticketCancelled.getPassengerID())) {
                passengerOwner = passenger;
            }
        }
        double refundMoney = 0;
        for (PaymentOperation operation : AirlineTicketSystem.getInstance().getPaymentHistory()) {
            refundMoney = operation.getPaymentPerTicket().get(ticketCancelled.getTicketID());
        }
        double discountUsed = ((100 * refundMoney) / ticketCancelled.getPrice()) / 100;
        passengerOwner.getTicketsList().remove(ticketCancelled);
        UserController userController = new UserController();
        passengerOwner.setCredits(passengerOwner.getCredits() + userController.deductCredit(discountUsed));
        DatabaseController.updatePassengerCreditRecord(passengerOwner);
        CancelOperation cancelOperation = new CancelOperation(passengerOwner.getUserID(), refundMoney, ticketCancelled.getTicketID(), userController.deductCredit(discountUsed));
        DatabaseController.insertCancelOperationRecordIntoOperation(cancelOperation, passengerOwner, discountUsed, refundMoney);
        passengerOwner.getRefundList().add(cancelOperation);
    }
}
