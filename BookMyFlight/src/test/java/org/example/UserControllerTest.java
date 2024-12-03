package org.example;

import org.example.controller.UserController;
import org.example.model.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserControllerTest {
    protected static Airplane airplane = new Airplane();
    protected static Passenger p = new Regular("Rauw", "John", "1234567890", "rauw@gmail.com", "kash#!$H");
    protected static UserController u = new UserController(p);

    @Test
    void payForTickets_CalculateSum() {
        Airplane a = new Airplane("BigFlights", 90, "Big");
        Ticket ticket = new Ticket( a, "2024-12-01", "2024-12-10", 100.0, "One-way", TicketStatus.UNBOOKED, SeatClass.ECONOMY, "NYC", "LAX");
        List<Ticket> tickets = new ArrayList<>(List.of(ticket));
        p.setCredits(500);
        double expResult = 95.00;
        double result = u.payForTickets(p, tickets);
        assertEquals(expResult, result);
    }

    @Test
    void removeSeatFromAirplane_RemoveASeatFrom100AvailableSeats() {
        Airplane a = new Airplane("BigFlights", 100, "Big");
        Ticket ticket = new Ticket( a, "2024-12-01", "2024-12-10", 200.0, "One-way", TicketStatus.UNBOOKED, SeatClass.ECONOMY, "NYC", "LAX");
        int expResult = 99;
        int result = u.removeSeatFromAirplane(ticket);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_500Credit() {
        double expResult = 0.05;
        double result = u.calculateDiscountWithCredits(500);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_1000Credit() {
        double expResult = 0.1;
        double result = u.calculateDiscountWithCredits(1000);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_1500Credit() {
        double expResult = 0.15;
        double result = u.calculateDiscountWithCredits(1500);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_2000Credit() {
        double expResult = 0.2;
        double result = u.calculateDiscountWithCredits(2000);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_0Credit() {
        double expResult = 0.0;
        double result = u.calculateDiscountWithCredits(0);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_3000Credit() {
        double expResult = 0.2;
        double result = u.calculateDiscountWithCredits(3000);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_FivePercent() {
        int expResult = 500;
        int result = u.deductCredit(0.05);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_TenPercent() {
        int expResult = 1000;
        int result = u.deductCredit(0.1);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_FifteenPercent() {
        int expResult = 1500;
        int result = u.deductCredit(0.15);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_TwentyPercent() {
        int expResult = 2000;
        int result = u.deductCredit(0.2);
        assertEquals(expResult, result);
    }

    @Test
    void calculateDiscountWithCredits_NoDiscount() {
        int expResult = 0;
        int result = u.deductCredit(0);
        assertEquals(expResult, result);
    }
}
