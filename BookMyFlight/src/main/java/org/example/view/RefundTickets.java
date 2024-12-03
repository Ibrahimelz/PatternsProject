package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefundTickets {
    private JPanel refundTicketsPanel;
    private JButton logoutButton;

    public RefundTickets(CardLayout cardLayout, JPanel cardPanel) {
        refundTicketsPanel = new JPanel();
        logoutButton = new JButton("Logout");
        refundTicketsPanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Login");
            }
        });

        cardPanel.add(refundTicketsPanel, "RefundTickets");
    }

    public JPanel getRefundTicketsPanel() {
        return refundTicketsPanel;
    }
}
