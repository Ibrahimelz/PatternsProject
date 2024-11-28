package org.example;

import org.example.model.AirlineTicketSystem;
import org.example.model.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeTest {

    @Test
    void testAddEmployee() {
        Employee newEmployee = new Employee("Jhon", "Alex", "1234567890", "something@gmail.com", "123456");
        AirlineTicketSystem airlineTicketSystem = AirlineTicketSystem.getInstance();
        airlineTicketSystem.getEmployees().add(newEmployee);
        assertTrue(airlineTicketSystem.getEmployees().contains(newEmployee), "New employee should be added successfully");
    }
}
