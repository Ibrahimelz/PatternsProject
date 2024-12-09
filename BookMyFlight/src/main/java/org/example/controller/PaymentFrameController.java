package org.example.controller;

import org.example.model.*;
import org.example.view.PaymentFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PaymentFrameController {
    private PaymentFrame paymentFrame;

    public PaymentFrameController(Passenger passenger) {
        paymentFrame = new PaymentFrame();
        if (passenger instanceof Regular) {
            Regular regular = (Regular) passenger;
            String totalCredit = String.valueOf(regular.getCredits());
            paymentFrame.getPayment().getDisplayCreditAmountLabel().setText(totalCredit);
        } else {
            Premium premium = (Premium) passenger;
            String totalCredit = String.valueOf(premium.getCredits() + premium.getBONUS_CREDIT());
            paymentFrame.getPayment().getDisplayCreditAmountLabel().setText(totalCredit);
        }
        paymentFrame.getPayment().getCheckoutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean unavailableTicket = false;
                List<Ticket> chosenTickets = selectAllTickets();
                if (chosenTickets.size() == 0) {
                    paymentFrame.getPayment().getErrorLabel().setText("Choose a Ticket First!");
                } else {
                    for (Ticket ticket : chosenTickets) {
                        if (ticket.getAirplane().getAvailableSeats() == 0) {
                            paymentFrame.getPayment().getErrorLabel().setText("Ticket " + ticket.getTicketID() + "is not available anymore!");
                            unavailableTicket = true;
                            break;
                        }
                    }
                    if (!unavailableTicket) {
                        new BuyTicketFrameController(chosenTickets, passenger, false);
                        paymentFrame.setVisible(false);
                    }
                }
            }
        });
        paymentFrame.getPayment().getAllTicketsComboBox().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!paymentFrame.getPayment().getAllTicketsComboBox().getSelectedItem().toString().equals("Select a ticket")) {
                    Ticket ticket = AirLineTicketSystemController.findTicket(paymentFrame.getPayment().getAllTicketsComboBox().getSelectedItem().toString());
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
                    paymentFrame.getPayment().getDisplayTicketIdLabel().setText(ticketId);
                    paymentFrame.getPayment().getDisplayTripTypeLabel().setText(tripType);
                    paymentFrame.getPayment().getDisplayOutboundDateLabel().setText(outboundDate);
                    paymentFrame.getPayment().getDisplayReturnDateLabel().setText(returnDate);
                    paymentFrame.getPayment().getTicketPriceLabel().setText(price);
                    paymentFrame.getPayment().getDisplaySeatClassLabel().setText(seatType);
                    paymentFrame.getPayment().getDisplayDepartureLabel().setText(departure);
                    paymentFrame.getPayment().getDisplayDestinationLabel().setText(destination);
                    paymentFrame.getPayment().getDisplayAirplaneLabel().setText(airplaneAirline);
                    paymentFrame.getPayment().getDisplayStatusLabel().setText(status);
                }
            }
        });
        paymentFrame.getPayment().getLanguageButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (paymentFrame.getPayment().getLanguageButton().getText().equals("en")) {
                    paymentFrame.setLanguage("en");
                    paymentFrame.changeLanguage();
                    paymentFrame.getPayment().getLanguageButton().setText("fr");
                } else {
                    paymentFrame.setLanguage("fr");
                    paymentFrame.changeLanguage();
                    paymentFrame.getPayment().getLanguageButton().setText("en");
                }
            }
        });
    }

    /**
     * Selects all the tickets that the Passenger choose
     * @return the list of tickets
     */
    public List<Ticket> selectAllTickets() {
        List<Ticket> tickets = new ArrayList<>();
        for (Component component : paymentFrame.getPayment().getPaymentPanel().getComponents()) {
            if (component instanceof JCheckBox) {
                if (((JCheckBox) component).isSelected()) {
                    tickets.add(AirLineTicketSystemController.findTicket(((JCheckBox) component).getText()));
                }
            }
        }
        return tickets;
    }
}
