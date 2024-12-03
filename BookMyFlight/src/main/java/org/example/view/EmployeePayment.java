package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EmployeePayment {
    private JPanel employeePaymentPanel;
    private JButton checkoutButton;
    private JButton exitButton;

    public EmployeePayment(CardLayout cardLayout, JPanel cardPanel) {
        employeePaymentPanel = new JPanel();
        checkoutButton = new JButton("Checkout");
        exitButton = new JButton("Exit");
        employeePaymentPanel.add(checkoutButton);
        employeePaymentPanel.add(exitButton);

        checkoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "BuyTicket");
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "RefundTickets");
            }
        });

        cardPanel.add(employeePaymentPanel, "EmployeePayment");
    }

    public JPanel getEmployeePaymentPanel() {
        return employeePaymentPanel;
    }
}
