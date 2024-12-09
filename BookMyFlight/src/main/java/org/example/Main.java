package org.example;

import org.example.controller.*;
import org.example.model.*;
import javax.xml.crypto.Data;

public class Main {
    public static void main(String[] args) {
        Airplane airplane = new Airplane("Qatar Airways", 150, "Boeing 707");
        Airplane airplane2 = new Airplane("Emirates", 200, "Boeing 747-8");
        Airplane airplane3 = new Airplane("Canada Airlines", 150, "Boeing 707");
        Airplane airplane4 = new Airplane("Japan Airlines", 100, "Boeing 747-8");
        Airplane airplane5 = new Airplane("Turkish Airways", 140, "Boeing 707");

//      DatabaseController.insertAirplaneRecord(airplane);
//      DatabaseController.insertAirplaneRecord(airplane2);
//      DatabaseController.insertAirplaneRecord(airplane3);
//      DatabaseController.insertAirplaneRecord(airplane4);
//      DatabaseController.insertAirplaneRecord(airplane5);

        Ticket ticket = new Ticket(airplane, "2024-12-20", "2024-12-30", 500.0, "Two-way", TicketStatus.UNBOOKED, SeatClass.ECONOMY, "Lebanon", "Montreal");
        Ticket ticket2 = new Ticket(airplane2, "2024-12-20", "2024-12-30", 2500.0, "Two-way", TicketStatus.UNBOOKED, SeatClass.BUSINESS, "Dubai", "New York");
        Ticket ticket3 = new Ticket(airplane3, "2024-09-20", "2024-10-20", 1500.0, "Two-way", TicketStatus.UNBOOKED, SeatClass.BUSINESS, "Montreal", "Paris");
        Ticket ticket4 = new Ticket(airplane4, "2024-07-27", "2024-08-18", 3500.0, "Two-way", TicketStatus.UNBOOKED, SeatClass.FIRST_CLASS, "Japan", "Montreal");
        Ticket ticket5 = new Ticket(airplane5, "2024-01-06", "2024-01-16", 1000.0, "Two-way", TicketStatus.UNBOOKED, SeatClass.BUSINESS, "Türkiye", "Paris");

//      DatabaseController.insertTicketRecord(ticket);
//      DatabaseController.insertTicketRecord(ticket2);
//      DatabaseController.insertTicketRecord(ticket3);
//      DatabaseController.insertTicketRecord(ticket4);
//      DatabaseController.insertTicketRecord(ticket5);

        AirLineTicketSystemController airLineTicketSystemController = new AirLineTicketSystemController();
        LoginFrameController loginFrameController = new LoginFrameController();
        loginFrameController.getLoginFrame().setVisible(true);
    }
}
