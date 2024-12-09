package org.example.controller;

import org.example.model.Employee;
import org.example.model.Passenger;
import org.example.model.User;
import org.example.view.CreateAccountFrame;
import org.example.view.LoginFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginFrameController {
    private LoginFrame loginFrame;

    public LoginFrameController() {
        this.loginFrame = new LoginFrame();
        loginFrame.getLoginForm().getCreateAccountButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CreateAccountFrameController createAccountController = new CreateAccountFrameController();
                loginFrame.setVisible(false);
            }
        });
        loginFrame.getLoginForm().getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = loginFrame.getLoginForm().getEmailTextField().getText();
                User user = AirLineTicketSystemController.findUserInSystem(email);
                if (user == null) {
                    loginFrame.getLoginForm().getErrorLabel().setText("User Does Not Exist!");
                } else {
                    String enteredPassword = loginFrame.getLoginForm().getPasswordField().getText();
                    if (user instanceof Passenger) {
                        Passenger passenger = (Passenger) user;
                        if (!enteredPassword.equals(passenger.getPassword())) {
                            loginFrame.getLoginForm().getErrorLabel().setText("Password doesn't match account!");
                        } else {
                            new PaymentFrameController(passenger);
                            loginFrame.dispose();
                        }
                    } else if (user instanceof Employee) {
                        Employee employee = (Employee) user;
                        if (!enteredPassword.equals(employee.getPassword())) {
                            loginFrame.getLoginForm().getErrorLabel().setText("Password doesn't match account!");
                        } else {
                            if (loginFrame.getLoginForm().getIsEmployeeCheckBox().isSelected()) {
                                new EmployeePaymentFrameController(employee);
                                loginFrame.dispose();
                            } else {
                                loginFrame.getLoginForm().getErrorLabel().setText("This is an Employee Account!");
                            }
                        }
                    }
                }
            }
        });
        loginFrame.getLoginForm().getLanguageButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginFrame.getLoginForm().getLanguageButton().getText().equals("en")) {
                    loginFrame.setLanguage("en");
                    loginFrame.changeLanguage();
                    loginFrame.getLoginForm().getLanguageButton().setText("fr");
                } else {
                    loginFrame.setLanguage("fr");
                    loginFrame.changeLanguage();
                    loginFrame.getLoginForm().getLanguageButton().setText("en");
                }
            }
        });
    }

    public LoginFrame getLoginFrame() {
        return loginFrame;
    }
}
