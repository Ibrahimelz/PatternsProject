package org.example.controller;

import org.example.model.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class EmployeeController {
    private Employee employee;

    /**
     * Adds an employee after their inputted data is validated
     * @param firstName   of the employee
     * @param LastName    of the employee
     * @param phoneNumber of the employee
     * @param email       of the employee
     * @param password    of the employee
     */
    public void addEmployee(String firstName, String LastName, String phoneNumber, String email, String password) {
        if (!employee.validateInputUserCreation(firstName, LastName, phoneNumber, email, password)) {
            throw new IllegalArgumentException("Passenger information provided is incorrect!");
        }
        if (AirLineTicketSystemController.findUserInSystem(email) != null) {
            throw new IllegalArgumentException("Account already exists!");
        }
        Employee employee = new Employee(firstName, LastName, phoneNumber, email, password);
        AirlineTicketSystem.getInstance().getEmployees().add(employee);
    }


    /**
     * Removes ticket from passengers booked tickets list
     * @return ticket that has been cancelled
     */
    public Ticket removeTicket() {
        Ticket ticketCancelled = employee.getTicketsToCancel().peek();
        double refundedMoney = 0;
        Passenger passengerOwner = null;
        for (Passenger passenger : AirlineTicketSystem.getInstance().getPassengers()) {
            if (passenger.getUserID().equals(ticketCancelled.getPassengerID())) {
                passengerOwner = passenger;
            } else {
                return null;
            }
        }
        double totalAmount = 0;
        for (PaymentOperation operation : AirlineTicketSystem.getInstance().getPaymentHistory()) {
            totalAmount = operation.getPaymentPerTicket().get(ticketCancelled.getTicketID());
        }
        double refundMoney = totalAmount;
        double discountUsed = ((100 * refundMoney) / ticketCancelled.getPrice()) / 100;
        passengerOwner.getTicketsList().remove(ticketCancelled);
        UserController userController = new UserController();
        passengerOwner.setCredits(passengerOwner.getCredits() + userController.deductCredit(discountUsed));
        CancelOperation cancelOperation = new CancelOperation(passengerOwner.getUserID(), refundMoney, ticketCancelled.getTicketID(), userController.deductCredit(discountUsed));
        passengerOwner.getRefundList().add(cancelOperation);
        return ticketCancelled;
    }

    /**
     * Writes a list of comma-separated strings into a file
     * @param path the path of the file
     * @param data the data to write
     */
    public static void writeFileTable(String path, List<String> data) {
        File file = new File(path);
        try (FileWriter fw = new FileWriter(file, true)) {
            for (String line : data) {
                fw.write(line + "\n");
            }
        } catch (IOException e) {
            System.out.println(String.format("%s: %s", e.getClass(), e.getMessage()));
        }
    }



}
