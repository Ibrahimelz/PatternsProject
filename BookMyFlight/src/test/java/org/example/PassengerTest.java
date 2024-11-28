package org.example;

import org.example.controller.PassengerController;
import org.example.model.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    @Test
    void testAddRegularPassenger() {
        Passenger passenger = new Passenger("John", "Doe", "1234567890", "john.doe@example.com", "123456");
        PassengerController controller = new PassengerController();
        boolean added = controller.addRegularPassenger("Jhon","Something","1234567890","jhon@example.com","123456");
        assertTrue(added, "Regular passenger should be added successfully");
    }

    @Test
    void testVerifySeatsAvailable() {
        Passenger passenger = new Passenger("John", "Doe", "1234567890", "john.doe@example.com", "123456");
        Ticket ticket1 = new Ticket("T001", new Airplane(111,"AirCanada",300,"Boeing 777"), "2024-12-01", "2024-12-10", 200.0, "One-way", TicketStatus.UNBOOKED, SeatClass.ECONOMY, "NYC", "LAX");
        try {
            passenger.verifySeatsAvailable(ticket1);
        } catch (IllegalArgumentException e) {
            fail("Exception should not be thrown when seats are available");
        }
    }
}
