package org.example;

import org.example.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;

public class UserTest {

    @Test
    void bookTickets_PassengerNotFound() {
        Passenger passenger = new Passenger("Charlie", "Brown", "1234561234", "charlie.brown@example.com", "123456");
        passenger.setCredits(1000);
        Ticket ticket = new Ticket("T003", new Airplane(111,"AirCanada",300,"Boeing 777"), "2024-12-01", "2024-12-10", 150.0, "One-way", TicketStatus.UNBOOKED, SeatClass.ECONOMY, "LAX", "NYC");
        List<Ticket> tickets = new ArrayList<>();
        tickets.add(ticket);
        User employee = new Employee("Eve", "Adams", "1112223334", "eve.adams@example.com", "123456");
        assertThrows(IllegalArgumentException.class, () -> employee.bookTickets(passenger, tickets), "Wrong Passenger Credentials");
    }
}
