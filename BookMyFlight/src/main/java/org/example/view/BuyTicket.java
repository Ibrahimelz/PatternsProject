package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuyTicket {
    private JPanel buyTicketPanel;
    private JButton payTicketsButton;
    private JCheckBox isEmployeeCheckBox;

    public BuyTicket(CardLayout cardLayout, JPanel cardPanel) {
        buyTicketPanel = new JPanel();
        payTicketsButton = new JButton("Pay Tickets");
        isEmployeeCheckBox = new JCheckBox("Are you an employee?");
        buyTicketPanel.add(isEmployeeCheckBox);
        buyTicketPanel.add(payTicketsButton);

        payTicketsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEmployeeCheckBox.isSelected()) {
                    cardLayout.show(cardPanel, "RefundTickets");
                } else {
                    cardLayout.show(cardPanel, "Exit");
                }
            }
        });

        cardPanel.add(buyTicketPanel, "BuyTicket");
    }

    public JPanel getBuyTicketPanel() {
        return buyTicketPanel;
    }
}
