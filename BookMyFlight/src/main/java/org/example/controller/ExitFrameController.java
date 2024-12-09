package org.example.controller;

import org.example.model.Passenger;
import org.example.model.Ticket;
import org.example.view.ExitFrame;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ExitFrameController {
    private ExitFrame exitFrame;
    private List<Ticket> selectedTickets;

    public ExitFrameController(List<Ticket> ticketsToCancel, Passenger passenger) {
        exitFrame = new ExitFrame();
        selectedTickets = new ArrayList<>();
        DefaultListModel<Ticket> listModel = new DefaultListModel<>();
        for (Ticket ticket : ticketsToCancel) {
            listModel.addElement(ticket);
        }
        exitFrame.getExit().getAllChosenTicketsList().setModel(listModel);
        exitFrame.getExit().getAllChosenTicketsList().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        exitFrame.getExit().getRefundTicketsButton().setEnabled(false);
        exitFrame.getExit().getAllChosenTicketsList().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    selectedTickets.clear();
                    selectedTickets.addAll(exitFrame.getExit().getAllChosenTicketsList().getSelectedValuesList());
                    exitFrame.getExit().getRefundTicketsButton().setEnabled(!selectedTickets.isEmpty());
                }
            }
        });
        exitFrame.getExit().getRefundTicketsButton().addActionListener(e -> {
            PassengerController passengerController = new PassengerController(passenger);
            for (Ticket tick : selectedTickets) {
                passengerController.requestRefund(tick);
            }
        });
        exitFrame.getExit().getLogoutButton().addActionListener(e -> {
            new LoginFrameController();
            exitFrame.setVisible(false);
        });
        exitFrame.getExit().getLanguageButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (exitFrame.getExit().getLanguageButton().getText().equals("en")) {
                    exitFrame.setLanguage("en");
                    exitFrame.changeLanguage();
                    exitFrame.getExit().getLanguageButton().setText("fr");
                } else {
                    exitFrame.setLanguage("fr");
                    exitFrame.changeLanguage();
                    exitFrame.getExit().getLanguageButton().setText("en");
                }
            }
        });
    }
}
