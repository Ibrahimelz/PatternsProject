package org.example;

import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.Regular;
import org.example.model.Ticket;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Executable;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class EmployeeTest {

    @Test
    void bookTickets_BookTicketsForNonexistentPassenger() {
        Employee e = new Employee();
        Ticket t = new Ticket();
        List<Ticket> tickets = new ArrayList<>(List.of(t));
        Passenger p = new Regular("Jeffrey", "Johnston", "2345678921", "jeffrey@gmail.com", "123@snadLDB3$@2");
        Exception executable = assertThrows(IllegalArgumentException.class, () -> e.bookTickets(p, tickets));
        assertEquals("Wrong Passenger Credentials", executable.getMessage());
    }
}
