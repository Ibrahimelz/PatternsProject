package org.example.controller;

import org.example.model.CancelOperation;
import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.Ticket;
import org.example.view.RefundTicketsFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefundTicketsFrameController {
    private RefundTicketsFrame refundTicketsFrame;

    public RefundTicketsFrameController(Passenger passenger) {
        refundTicketsFrame = new RefundTicketsFrame();
        DefaultListModel<Ticket> listModel = new DefaultListModel<>();
        for (Ticket ticket : Employee.getTicketsToCancel()) {
            listModel.addElement(ticket);
        }
        refundTicketsFrame.getRefundTickets().getAllChosenTicketsList().setModel(listModel);
        refundTicketsFrame.getRefundTickets().getAllChosenTicketsList().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refundTicketsFrame.getRefundTickets().getAllChosenTicketsList().setEnabled(false);
        refundTicketsFrame.getRefundTickets().getDisplayNextTicketToCancel().setText(String.valueOf(Employee.getTicketsToCancel().peek()));
        refundTicketsFrame.getRefundTickets().getRefundTicketsButton().addActionListener(e -> {
            EmployeeController.removeTicket();
        });
        refundTicketsFrame.getRefundTickets().getLogoutButton().addActionListener(e -> {
            new LoginFrameController();
            refundTicketsFrame.setVisible(false);
        });
        refundTicketsFrame.getRefundTickets().getLanguageButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (refundTicketsFrame.getRefundTickets().getLanguageButton().getText().equals("en")) {
                    refundTicketsFrame.setLanguage("en");
                    refundTicketsFrame.changeLanguage();
                    refundTicketsFrame.getRefundTickets().getLanguageButton().setText("fr");
                } else {
                    refundTicketsFrame.setLanguage("fr");
                    refundTicketsFrame.changeLanguage();
                    refundTicketsFrame.getRefundTickets().getLanguageButton().setText("en");
                }
            }
        });
    }
}
