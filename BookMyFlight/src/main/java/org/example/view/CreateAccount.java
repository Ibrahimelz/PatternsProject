package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CreateAccount {
    private JPanel createAccountPanel;
    private JButton createAccountButton;
    private JTextField firstNameTextField;
    private JTextField lastNameTextField;
    private JTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField passwordTextField;

    // Constructor with CardLayout and JPanel reference
    public CreateAccount(CardLayout cardLayout, JPanel cardPanel) {
        createAccountPanel = new JPanel();

        // Labels and fields
        firstNameTextField = new JTextField();
        lastNameTextField = new JTextField();
        phoneNumberTextField = new JTextField();
        emailTextField = new JTextField();
        passwordTextField = new JPasswordField();
        createAccountButton = new JButton("Create Account");

        // Add components to panel
        createAccountPanel.add(new JLabel("First Name:"));
        createAccountPanel.add(firstNameTextField);
        createAccountPanel.add(new JLabel("Last Name:"));
        createAccountPanel.add(lastNameTextField);
        createAccountPanel.add(new JLabel("Phone Number:"));
        createAccountPanel.add(phoneNumberTextField);
        createAccountPanel.add(new JLabel("Email:"));
        createAccountPanel.add(emailTextField);
        createAccountPanel.add(new JLabel("Password:"));
        createAccountPanel.add(passwordTextField);
        createAccountPanel.add(createAccountButton);

        // Button action listener to go back to LoginForm
        createAccountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(cardPanel, "Login");  // Switch back to the login form
            }
        });

        // Add this panel to the cardPanel
        cardPanel.add(createAccountPanel, "CreateAccount");
    }

    // Method to return this panel (if needed externally)
    public JPanel getCreateAccountPanel() {
        return createAccountPanel;
    }
}
