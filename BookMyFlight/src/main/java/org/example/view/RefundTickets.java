package org.example.view;

import lombok.Getter;

import javax.swing.*;


public class RefundTickets {
    private JPanel refundTicketsPanel;
    private JButton logoutButton;
    private JButton refundTicketsButton;
    private JList allChosenTicketsList;
    private JLabel ticketCancelLabel;
    private JLabel displayNextTicketToCancel;
    private JLabel instructionsLabel;
    private JButton languageButton;

    public JPanel getRefundTicketsPanel() {
        return refundTicketsPanel;
    }

    public JButton getRefundTicketsButton() {
        return refundTicketsButton;
    }

    public JList getAllChosenTicketsList() {
        return allChosenTicketsList;
    }

    public JLabel getTicketCancelLabel() {
        return ticketCancelLabel;
    }

    public JLabel getDisplayNextTicketToCancel() {
        return displayNextTicketToCancel;
    }

    public JLabel getInstructionsLabel() {
        return instructionsLabel;
    }

    public JButton getLanguageButton() {
        return languageButton;
    }

    public JButton getLogoutButton() {
        return logoutButton;
    }
}
