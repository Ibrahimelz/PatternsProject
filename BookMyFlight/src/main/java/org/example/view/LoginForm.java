package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {


    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel; // Main container panel for CardLayout
    private JPanel loginPanel;
    private JPanel paymentPanel;
    private JPanel employeePaymentPanel;
    private JPanel createAccountPanel;
    private JButton loginButton;
    private JButton createAccountButton;
    private JTextField emailTextField;
    private JTextField passwordTextField;
    private JCheckBox isEmployeeCheckBox;

    public LoginForm() {
        frame = new JFrame("Main Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300); // Adjust size as needed

        // Set up CardLayout
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        // Initialize panels
        loginPanel = new JPanel();
        paymentPanel = new Payment().getPaymentPanel();
        employeePaymentPanel = new EmployeePayment().getEmployeePaymentPanel();
        createAccountPanel = new CreateAccount().getCreateAccountPanel();

        // Add components to login panel
        emailTextField = new JTextField();
        passwordTextField = new JPasswordField();
        loginButton = new JButton("Login");
        createAccountButton = new JButton("Create Account");
        isEmployeeCheckBox = new JCheckBox("Employee");

        loginPanel.add(new JLabel("Email:"));
        loginPanel.add(emailTextField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordTextField);
        loginPanel.add(loginButton);
        loginPanel.add(createAccountButton);

        // Add panels to CardLayout container
        cardPanel.add(loginPanel, "Login");
        cardPanel.add(paymentPanel, "Payment");
        cardPanel.add(employeePaymentPanel, "EmployeePayment");
        cardPanel.add(createAccountPanel, "CreateAccount");

        // Add CardLayout container to frame
        frame.add(cardPanel);
        cardLayout.show(cardPanel, "Login"); // Show login panel initially

        // Button actions
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isEmployeeCheckBox.isSelected()) {
                    cardLayout.show(cardPanel, "EmployeePayment");
                } else {
                    cardLayout.show(cardPanel, "Payment");
                }
            }
        });

        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "CreateAccount");
            }
        });

        frame.setVisible(true);
    }

    public JPanel getLoginPanel() {
        return loginPanel;
    }
}
