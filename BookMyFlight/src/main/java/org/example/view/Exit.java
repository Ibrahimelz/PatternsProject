package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Exit {
    private JPanel exitPanel;
    private JButton refundTicketsButton;
    private JButton logoutButton;

    public Exit(CardLayout cardLayout, JPanel cardPanel) {
        exitPanel = new JPanel();
        refundTicketsButton = new JButton("Refund Tickets");
        logoutButton = new JButton("Logout");

        exitPanel.add(refundTicketsButton);
        exitPanel.add(logoutButton);

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Login");
            }
        });

        cardPanel.add(exitPanel, "Exit");
    }

    public JPanel getExitPanel() {
        return exitPanel;
    }
}
