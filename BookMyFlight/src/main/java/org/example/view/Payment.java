package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Payment {
    private JPanel paymentPanel;
    private JButton checkoutButton;
    private JButton exitButton;
    // Other components...

    public Payment(CardLayout cardLayout, JPanel cardPanel) {
        paymentPanel = new JPanel();

        // Initialize and add components to panel (simplified for brevity)
        checkoutButton = new JButton("Checkout");
        exitButton = new JButton("Exit");
        paymentPanel.add(checkoutButton);
        paymentPanel.add(exitButton);

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "BuyTicket"); // Switch to BuyTicketPanel
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Exit"); // Switch to ExitPanel
            }
        });

        cardPanel.add(paymentPanel, "Payment");
    }

    public JPanel getPaymentPanel() {
        return paymentPanel;
    }
}
