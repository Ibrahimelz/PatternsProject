package org.example;

import org.example.controller.AirLineTicketSystemController;
import org.example.model.AirlineTicketSystem;
import org.example.model.Passenger;
import org.example.model.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirlineTicketSystemTest {

    @Test
    void testFindUserInSystem() {
        AirlineTicketSystem airlineTicketSystem = AirlineTicketSystem.getInstance();
        AirLineTicketSystemController controller = new AirLineTicketSystemController();
        Passenger expectedUser = new Passenger("John", "Doe", "1234567890", "john.doe@example.com", "password123");
        airlineTicketSystem.getPassengers().add(expectedUser);
        User userFound = controller.findUserInSystem("john.doe@example.com");
        assertNotNull(userFound, "User should be found in the system");
        assertEquals(expectedUser.getEmail(), userFound.getEmail(), "The found user should have the same email as the expected one");
    }
}
