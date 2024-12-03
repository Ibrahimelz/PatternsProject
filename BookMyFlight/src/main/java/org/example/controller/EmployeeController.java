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
    private Employee employee;

    /**
     * Adds an employee after their inputted data is validated
     * @param firstName   of the employee
     * @param LastName    of the employee
     * @param phoneNumber of the employee
     * @param email       of the employee
     * @param password    of the employee
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
    public void removeTicket() {
        Ticket ticketCancelled = employee.getTicketsToCancel().peek();

        Passenger passengerOwner = null;

        if (employee.getTicketsToCancel().size() == 0) {
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
        UserController userController = new UserController(employee);
        passengerOwner.setCredits(passengerOwner.getCredits() + userController.deductCredit(discountUsed));
        CancelOperation cancelOperation = new CancelOperation(passengerOwner.getUserID(), refundMoney, ticketCancelled.getTicketID(), userController.deductCredit(discountUsed));
        passengerOwner.getRefundList().add(cancelOperation);
        //OR RETURN THE ticketCancelled
    }

    /**
     * Writes a list of comma-separated strings into a file
     * @param operation cancel operation that will be "printed" as a proof of receipt
     */
    public static void writeRefundFile(CancelOperation operation) {
        LocalTime time = LocalTime.now();
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH-mm-ss");
        String formattedTime = time.format(timeFormatter);

        String userHome = System.getProperty("user.home");
        String desktopPath = userHome + "/Desktop";
        String filePath = desktopPath + "/refund" + formattedTime + ".txt";

        try {
            FileWriter myWriter = new FileWriter(filePath);
            myWriter.write(operation.toString());
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
