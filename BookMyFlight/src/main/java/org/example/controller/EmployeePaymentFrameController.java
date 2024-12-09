package org.example.controller;

import org.example.model.*;
import org.example.view.EmployeePaymentFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class EmployeePaymentFrameController {
    private EmployeePaymentFrame employeePaymentFrame;

    public EmployeePaymentFrameController(Employee employee) {
        this.employeePaymentFrame = new EmployeePaymentFrame();
        employeePaymentFrame.getEmployeePayment().getCheckoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                List<Ticket> chosenTickets = selectAllTickets();
                String email = employeePaymentFrame.getEmployeePayment().getClientEmailTextBox().getText();
                boolean unavailableTicket = false;
                if (chosenTickets.isEmpty()) {
                    employeePaymentFrame.getEmployeePayment().getErrorLabel().setText("Choose a Ticket First!");
                } else {
                    if (AirLineTicketSystemController.findUserInSystem(email) == null) {
                        employeePaymentFrame.getEmployeePayment().getErrorLabel().setText("User does not exist!");
                    } else {
                        for (Ticket ticket : chosenTickets) {
                            if (ticket.getAirplane().getAvailableSeats() == 0) {
                                employeePaymentFrame.getEmployeePayment().getErrorLabel().setText("Ticket " + ticket.getTicketID() + "is not available anymore!");
                                unavailableTicket = true;
                                break;
                            }
                        }
                        if (!unavailableTicket) {
                            new BuyTicketFrameController(chosenTickets, (Passenger) AirLineTicketSystemController.findUserInSystem(email), true);
                            employeePaymentFrame.setVisible(false);
                        }
                    }
                }
            }
        });
        employeePaymentFrame.getEmployeePayment().getAllTicketsComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!employeePaymentFrame.getEmployeePayment().getAllTicketsComboBox().getSelectedItem().toString().equals("Select a ticket")) {
                    Ticket ticket = AirLineTicketSystemController.findTicket(employeePaymentFrame.getEmployeePayment().getAllTicketsComboBox().getSelectedItem().toString());
                    String ticketId = ticket.getTicketID();
                    String airplaneAirline = ticket.getAirplane().getAssignedAirline();
                    String outboundDate = ticket.getOutboundDate();
                    String returnDate = ticket.getReturnDate();
                    String price = String.valueOf(ticket.getPrice());
                    String tripType = ticket.getTripType();
                    String status = ticket.getStatus().name();
                    String seatType = ticket.getSeatType().name();
                    String departure = ticket.getDeparture();
                    String destination = ticket.getDestination();
                    employeePaymentFrame.getEmployeePayment().getDisplayTicketIdLabel().setText(ticketId);
                    employeePaymentFrame.getEmployeePayment().getDisplayTripTypeLabel().setText(tripType);
                    employeePaymentFrame.getEmployeePayment().getDisplayOutboundDateLabel().setText(outboundDate);
                    employeePaymentFrame.getEmployeePayment().getDisplayReturnDateLabel().setText(returnDate);
                    employeePaymentFrame.getEmployeePayment().getTicketPriceLabel().setText(price);
                    employeePaymentFrame.getEmployeePayment().getDisplaySeatClassLabel().setText(seatType);
                    employeePaymentFrame.getEmployeePayment().getDisplayDepartureLabel().setText(departure);
                    employeePaymentFrame.getEmployeePayment().getDisplayDestinationLabel().setText(destination);
                    employeePaymentFrame.getEmployeePayment().getDisplayAirplaneLabel().setText(airplaneAirline);
                    employeePaymentFrame.getEmployeePayment().getDisplayStatusLabel().setText(status);
                }
            }
        });
        employeePaymentFrame.getEmployeePayment().getLanguageButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (employeePaymentFrame.getEmployeePayment().getLanguageButton().getText().equals("en")) {
                    employeePaymentFrame.setLanguage("en");
                    employeePaymentFrame.changeLanguage();
                    employeePaymentFrame.getEmployeePayment().getLanguageButton().setText("fr");
                } else {
                    employeePaymentFrame.setLanguage("fr");
                    employeePaymentFrame.changeLanguage();
                    employeePaymentFrame.getEmployeePayment().getLanguageButton().setText("en");
                }
            }
        });
    }

    /**
     * Selects the tickets that the employee choose
     * @return the list of tickets
     */
    public List<Ticket> selectAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        for (Component component : employeePaymentFrame.getEmployeePayment().getEmployeePaymentPanel().getComponents()) {
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    tickets.add(AirLineTicketSystemController.findTicket(((JCheckBox) component).getText()));
                }
            }
        }
        return tickets;
    }
}
