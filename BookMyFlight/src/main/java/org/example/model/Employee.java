package org.example.model;

import lombok.*;
import org.example.controller.AirLineTicketSystemController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
public class Employee extends User {
    @Getter
    private static Queue<Ticket> ticketsToCancel = new PriorityQueue<>();

    public Employee(String firstName, String lastName, String phoneNumber, String email, String password) {
        super(firstName, lastName, phoneNumber, email, password);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "userID='" + userID + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
